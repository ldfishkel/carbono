package com.example.utils;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class InsecurePasswordChecker {

    @Getter @Setter private Set<String> insecurePasswords;

    public boolean checkPassword(String password) {
        return !this.insecurePasswords.contains(password);
    }
}