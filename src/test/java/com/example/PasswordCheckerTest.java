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

        Assert.assertEquals(false, checker.isSecure("pcorta"));
        Assert.assertEquals(false, checker.isSecure("123456"));
        Assert.assertEquals(true, checker.isSecure("un4PassS3gur4!"));
        Assert.assertEquals(false, checker.isSecure("un4passs3gur4!"));
        Assert.assertEquals(false, checker.isSecure("unapasssegura!"));
        Assert.assertEquals(false, checker.isSecure("UNAPASSSEGURA!"));
    }
}
