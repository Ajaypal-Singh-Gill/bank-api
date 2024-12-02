package com.example.assessment.controllers;

import com.example.assessment.DTOs.CreateUserAccountRequest;
import com.example.assessment.DTOs.CreateUserAccountResponse;
import com.example.assessment.DTOs.TransferFundsRequest;
import com.example.assessment.DTOs.TransferFundsResponse;
import com.example.assessment.models.Transaction;
import com.example.assessment.services.AccountService;
import com.example.assessment.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create_new_user_account", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse<CreateUserAccountResponse> createNewUserWithAccount( @Valid @RequestBody CreateUserAccountRequest createUserAccountRequest) {
        System.out.println("Request received for creating new user account" + createUserAccountRequest);
        try {
            CreateUserAccountResponse createUserAccountResponse = accountService.createNewUserWithAccount(createUserAccountRequest);
            System.out.println("Response Sent" + ApiResponse.success("User and account created successfully", createUserAccountResponse));
            return ApiResponse.success("User and account created successfully", createUserAccountResponse);
        } catch (Exception e) {
            return ApiResponse.failure("User and account creation failed: " + e.getMessage(),null);

        }

    }

    @PostMapping(value = "/transfer_funds", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ApiResponse<TransferFundsResponse> transferFunds( @Valid @RequestBody TransferFundsRequest transferFundsRequest) {
        System.out.println("Request received for transferring funds" + transferFundsRequest);
        try {
            TransferFundsResponse transferFundsResponse = accountService.transferFunds(transferFundsRequest);
            System.out.println("Response sent" + ApiResponse.success("Funds transferred successfully", transferFundsResponse));
            return ApiResponse.success("Funds transferred successfully", transferFundsResponse);
        } catch (Exception e) {
            return ApiResponse.failure("Funds transferred failed: " + e.getMessage(),null);
        }
    }

    @GetMapping("/{accountId}/transactions")
    public ApiResponse<List<Transaction>> getTransactionHistory(@PathVariable Long accountId) {
        try {
            List<Transaction> transactions = accountService.retrieveTransactionHistory(accountId);
            return ApiResponse.success("Transaction history retrieved successfully", transactions);
        } catch (RuntimeException e) {
            return ApiResponse.failure("Transaction history retrieval failed: " + e.getMessage(),null);
        }
    }



}
