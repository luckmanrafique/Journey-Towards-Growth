package com.registration.condition.password;

public class DigitException extends Exception{
    public DigitException(){
        super("Password must contain a digit");
    }
}
