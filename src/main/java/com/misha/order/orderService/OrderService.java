package com.misha.order.orderService;

import com.misha.order.exceptions.BusinessException;
import com.misha.order.kafka.OrderProducer;
import com.misha.order.orderMapper.OrderMapper;
import com.misha.order.orderRepository.CustomerClient;
import com.misha.order.orderRepository.OrderRepository;
import com.misha.order.orderRequest.*;
import com.misha.order.payment.PaymentClient;
import com.misha.order.payment.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;


    public Integer createOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));
        var purchasedProducts = this.productClient.purchaseProduct(orderRequest.products());

        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        for(PurchaseRequest purchaseRequest: orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(
                paymentRequest
        );


        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                    orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }


    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());

    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new BusinessException("Cannot find order with provided id {}" + orderId));
    }

}
