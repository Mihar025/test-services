package com.misha.order.orderRequest;

import com.misha.order.model.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse (

        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {

}
