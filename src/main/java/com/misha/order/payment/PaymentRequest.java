package com.misha.order.payment;

import com.misha.order.model.PaymentMethod;
import com.misha.order.orderRequest.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
