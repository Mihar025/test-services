package com.misha.payment.paymentRequests;

import com.misha.payment.paymentRepository.model.Customer;
import com.misha.payment.paymentRepository.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest (
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
){
}
