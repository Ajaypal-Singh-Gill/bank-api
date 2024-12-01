package com.example.assessment.repository;

import com.example.assessment.models.Account;
import com.example.assessment.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryAccountRepo {
    private final Map<Long, Account> accountMap = new HashMap<>();
    private long idCounter = 1;

    public Account save(Account account) {
        if (account.getId() == -1) {
            account.setId(idCounter++);
        }
        accountMap.put(account.getId(), account);
        return account;
    }

    public Account findById(Long accountId) {
        return accountMap.get(accountId);
    }
}
