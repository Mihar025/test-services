package com.misha.payment.paymentRepository.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(

        String id,
        @NotNull(message = "Firstname is required")
        String firstName,
        @NotNull(message = "Lastname is required")
        String lastName,
        @Email(message = "Email should be in correct form: user@gmail.com")
        @NotNull(message = "Email is required")
        @NotBlank(message = "Field is mandatory!")
        String email
) {
}
