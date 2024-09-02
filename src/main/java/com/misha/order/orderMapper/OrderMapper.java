package com.misha.order.orderMapper;

import com.misha.order.model.Order;
import com.misha.order.orderRequest.OrderRequest;
import com.misha.order.orderRequest.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {


    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.orderId())
                .customer_id(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomer_id()
        );
    }
}
