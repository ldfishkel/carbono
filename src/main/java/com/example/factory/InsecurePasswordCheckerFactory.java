package com.example.factory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.example.utils.InsecurePasswordChecker;
import com.google.gson.Gson;

public class InsecurePasswordCheckerFactory {

    private static String FILE_NAME = "src/main/resources/insecurePasswords.json";
    
    public static InsecurePasswordChecker makeChecker() {

        Gson gson = new Gson();

        try (Reader reader = new FileReader(FILE_NAME)) {

            return gson.fromJson(reader, InsecurePasswordChecker.class);

        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }
}