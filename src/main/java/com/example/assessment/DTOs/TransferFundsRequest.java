package com.example.assessment.DTOs;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TransferFundsRequest {
    private Long fromAccountId;
    private Long toAccountId;

    @DecimalMin(value = "0.00", message = "Transfer amount must not be negative")
    private BigDecimal amount;
}
