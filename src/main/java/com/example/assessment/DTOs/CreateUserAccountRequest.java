package com.example.assessment.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@JsonSerialize
@ToString
@Getter
public class CreateUserAccountRequest {

    @NotBlank(message = "First name must not be null or empty")
    private String firstName;

    @NotBlank(message = "Last name must not be null or empty")
    private String lastName;

    @NotBlank(message = "Email must not be null or empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password must not be null or empty")
    private String password;

    @NotNull(message = "Balance must not be null")
    @DecimalMin(value = "0.00", message = "Initial balance must not be negative")
    private BigDecimal balance;
}