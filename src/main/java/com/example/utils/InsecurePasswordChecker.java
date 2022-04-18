package com.example.utils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

public class InsecurePasswordChecker {

    private static String VALIDATION_REGEXP = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])";
    
    @Getter @Setter private Set<String> insecurePasswords;

    /**
     *  Que contenga al menos una letra mayúscula.
     *  Que contenga al menos una letra minúscula.
     *  Que contenga al menos un numero.
     *  Que la longitud de la contraseña sea >=8.
     *  Que la contraseña no forme parte del top 10.000 de peores contraseñas.
     * @param password
     * @return
     */
    public boolean isSecure(String password) {
        return this.containsUpperLowerAndNumberChar(password) &&
               password.length() >= 8 &&
              !this.insecurePasswords.contains(password);
    }

    private boolean containsUpperLowerAndNumberChar(String password) {
        Pattern pattern = Pattern.compile(VALIDATION_REGEXP);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

}