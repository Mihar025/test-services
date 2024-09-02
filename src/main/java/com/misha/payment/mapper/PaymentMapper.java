package com.misha.payment.mapper;

import com.misha.payment.paymentRepository.model.Payment;
import com.misha.payment.paymentRequests.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .id(request.id())
                .amount(request.amount())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .build();
    }
}
