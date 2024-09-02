package com.misha.order.OrderController;

import com.misha.order.orderRequest.OrderLineResponse;
import com.misha.order.orderService.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId (
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }


}
