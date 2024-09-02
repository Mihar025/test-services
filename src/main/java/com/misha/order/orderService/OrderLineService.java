package com.misha.order.orderService;

import com.misha.order.orderMapper.OrderLineMapper;
import com.misha.order.orderRepository.OrderLineRepository;
import com.misha.order.orderRequest.OrderLineRequest;
import com.misha.order.orderRequest.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderMapper;


    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
            var order = orderMapper.toOrderLine(orderLineRequest);
             return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
