package com.example.assessment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private long id = -1;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
