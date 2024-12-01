package com.example.assessment.repository;

import com.example.assessment.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepo {
    private final Map<Long, User> userMap = new HashMap<>();
    private long idCounter = 1;

    public User save(User user) {
        if (user.getId() == -1) {
            user.setId(idCounter++);
        }
        userMap.put(user.getId(), user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return userMap.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

}
