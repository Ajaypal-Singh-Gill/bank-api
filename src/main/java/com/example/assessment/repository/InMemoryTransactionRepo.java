package com.example.assessment.repository;

import com.example.assessment.models.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTransactionRepo {
    private final Map<Long, List<Transaction>> transactionMap = new HashMap<>();
    private long idCounter = 1;

    public void save(Transaction transaction) {
        transaction.setId(idCounter++);
        long accountId = transaction.getAccountId();
        if (!transactionMap.containsKey(accountId)) {
            transactionMap.put(accountId, new ArrayList<>());
        }
        transactionMap.get(accountId).add(transaction);
    }

    public List<Transaction> findByAccountId(Long accountId) {
        if (!transactionMap.containsKey(accountId)) {
            return new ArrayList<>();
        }
        return transactionMap.get(accountId);
    }
}
