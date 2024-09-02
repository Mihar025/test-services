package com.misha.order.orderMapper;

import com.misha.order.model.Order;
import com.misha.order.model.OrderLine;
import com.misha.order.orderRequest.OrderLineRequest;
import com.misha.order.orderRequest.OrderLineResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build()
                )
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
