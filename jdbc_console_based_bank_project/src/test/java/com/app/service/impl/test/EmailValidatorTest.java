package com.app.service.impl.test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.bankingApp.service.EmailValidatorService;
import app.bankingApp.service.implementation.EmailValidatorServiceImpl;

@RunWith(Parameterized.class)
public class EmailValidatorTest 
{	 
    private String arg;
    private static EmailValidatorService emailValidator;
    private Boolean expectedValidation;
 
    public EmailValidatorTest(String str, Boolean expectedValidation) 
    {
        this.arg = str;
        this.expectedValidation = expectedValidation;
    }
 
    @BeforeClass
    public static void initialize() 
    {
        emailValidator = new EmailValidatorServiceImpl();
    }
 
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {"n!k@s",false },                         // it's less that 8 characters long 
                { "gregorymarjames-law", false },         // it doesn't contain an digits or upper case characters
                { " abcdFg45*", false },                  // characters ~ in not allowed     
                { "n!koabcD#AX", false },                 // there should be a digit  
                { "ABCASWF2!", false   },                 // there should be a lower case character
 
                // valid passwords
 
                {"n!k@sn1Kos",true },                         
                { "J@vaC0deG##ks", true },         
                { "n!k1abcD#!", true } };
 
        return Arrays.asList(data);
    }
 
    @Test
    public void test() {
        Boolean res = emailValidator.isEmailValid(arg);
        String validv = (res) ? "valid" : "invalid";
        System.out.println("Password "+arg+ " is " + validv);
        assertEquals("Result", this.expectedValidation, res);
    }
}
