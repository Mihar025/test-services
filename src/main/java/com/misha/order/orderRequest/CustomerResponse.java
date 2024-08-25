package com.misha.order.orderRequest;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email

) {
}
