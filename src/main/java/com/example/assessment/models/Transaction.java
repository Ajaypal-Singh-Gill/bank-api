package com.example.assessment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private long id;
    private long accountId;
    private BigDecimal amount;
    private String type; // "Debit" or "Credit"
    private String description; // Optional, e.g., "Transfer to Account 2"
    private LocalDateTime timestamp;
}
