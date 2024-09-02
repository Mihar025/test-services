package com.misha.order.orderRepository;

import com.misha.order.model.OrderLine;
import com.misha.order.orderRequest.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
