package com.example.assessment.services;

import com.example.assessment.DTOs.CreateUserAccountRequest;
import com.example.assessment.DTOs.CreateUserAccountResponse;
import com.example.assessment.DTOs.TransferFundsRequest;
import com.example.assessment.DTOs.TransferFundsResponse;
import com.example.assessment.models.Account;
import com.example.assessment.models.Transaction;
import com.example.assessment.models.User;
import com.example.assessment.repository.InMemoryAccountRepo;
import com.example.assessment.repository.InMemoryTransactionRepo;
import com.example.assessment.repository.InMemoryUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private InMemoryUserRepo userRepo;

    @Autowired
    private InMemoryAccountRepo accountRepo;

    @Autowired
    private InMemoryTransactionRepo transactionRepo;

    public CreateUserAccountResponse createNewUserWithAccount(CreateUserAccountRequest createUserAccountRequest) {
        if(userRepo.findByEmail(createUserAccountRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User(-1,createUserAccountRequest.getFirstName(), createUserAccountRequest.getLastName(), createUserAccountRequest.getEmail(), createUserAccountRequest.getPassword());
        User savedUser = userRepo.save(user);
        Account account = new Account(-1, savedUser.getId(), createUserAccountRequest.getBalance());
        Account savedAccount = accountRepo.save(account);

        return new CreateUserAccountResponse(savedUser.getId(),savedUser.getFirstName(),savedAccount.getId(), savedAccount.getBalance());
    }

    public TransferFundsResponse transferFunds(TransferFundsRequest transferFundsRequest) {
        validateTransferFunds(transferFundsRequest);

        Account fromAccount = accountRepo.findById(transferFundsRequest.getFromAccountId());
        Account toAccount = accountRepo.findById(transferFundsRequest.getToAccountId());

        if (fromAccount == null) {
            throw new RuntimeException("Source account not found");
        }

        if (toAccount == null) {
            throw new RuntimeException("Destination account not found");
        }

        final LocalDateTime now = LocalDateTime.now();

        if (fromAccount.getBalance().compareTo(transferFundsRequest.getAmount()) == -1) {
            transactionRepo.save(new Transaction(fromAccount.getId(), transferFundsRequest.getFromAccountId(), transferFundsRequest.getAmount().negate(), "Debit", "FAILED: Insufficient funds", now));
            throw new RuntimeException("Insufficient funds in source account");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferFundsRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferFundsRequest.getAmount()));

        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        String transactionId = generateTransactionId();
        transactionRepo.save(new Transaction(
                -1,
                transferFundsRequest.getFromAccountId(),
                transferFundsRequest.getAmount().negate(),
                "Debit",
                "Transferred to Account " + toAccount.getId(),
                now
        ));
        transactionRepo.save(new Transaction(
                -1,
                transferFundsRequest.getToAccountId(),
                transferFundsRequest.getAmount(),
                "Credit",
                "Received from Account " + fromAccount.getId(),
                now
        ));

        return new TransferFundsResponse(transactionId, fromAccount.getId(), toAccount.getId(), transferFundsRequest.getAmount(), "SUCCESS");
    }

    public List<Transaction> retrieveTransactionHistory(Long accountId) {
        Account account = accountRepo.findById(accountId);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        return transactionRepo.findByAccountId(accountId);
    }


    private void validateTransferFunds(TransferFundsRequest request) {
        if (request.getFromAccountId() == null || request.getToAccountId() == null) {
            throw new IllegalArgumentException("Account IDs must not be null");
        }
        if (request.getFromAccountId().equals(request.getToAccountId())) {
            throw new IllegalArgumentException("Source and destination accounts must be different");
        }
        if (request.getAmount().compareTo(new BigDecimal(0)) == -1) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString(); // Unique transaction ID
    }

}
