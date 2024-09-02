package com.misha.payment.requests;

import com.misha.payment.paymentRepository.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(

        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail


) {
}
