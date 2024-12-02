package com.example.assessment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class Account {
    private long id;
    private long userId;
    private BigDecimal balance;
}
