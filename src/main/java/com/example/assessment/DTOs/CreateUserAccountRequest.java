package com.example.assessment.DTOs;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email must have a valid format, eg john@gmail.com"
    )
    private String email;

    @NotBlank(message = "Password must not be null or empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Balance must not be null")
    @DecimalMin(value = "0.00", message = "Initial balance must not be negative")
    private BigDecimal balance;
}