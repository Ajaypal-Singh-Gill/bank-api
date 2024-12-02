package com.example.assessment.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TransferFundsRequest {

    @NotNull(message = "Source account must not be null or empty")
    private Long fromAccountId;

    @NotNull(message = "Destination account must not be null or empty")
    private Long toAccountId;

    @NotNull(message = "Transfer amount must not be null")
    @DecimalMin(value = "0.00", message = "Transfer amount must not be negative")
    private BigDecimal amount;
}
