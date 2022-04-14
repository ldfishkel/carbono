package com.example;

import com.example.factory.InsecurePasswordCheckerFactory;
import com.example.utils.InsecurePasswordChecker;

import org.junit.Assert;
import org.junit.Test;

public class PasswordCheckerTest {
    
    @Test
    public void passwordCheckerTest() {

        InsecurePasswordChecker checker = InsecurePasswordCheckerFactory.makeChecker();

        Assert.assertNotNull(checker);

        Assert.assertEquals(false, checker.checkPassword("123456"));
        Assert.assertEquals(true, checker.checkPassword("un4PassS3gur4!"));
    }
}
