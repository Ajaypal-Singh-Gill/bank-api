package com.example.assessment.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.math.BigDecimal;

@JsonSerialize
@Getter
public class CreateUserAccountResponse {
    private Long userId;
    private String firstName;
    private Long accountId;
    private BigDecimal balance;
    public CreateUserAccountResponse(Long userId, String firstName, Long accountId, BigDecimal balance) {
        this.userId = userId;
        this.firstName = firstName;
        this.accountId = accountId;
        this.balance = balance;
    }
}
