package com.registraion.condition.password;

public class DigitException extends Exception{
    public DigitException(){
        super("Password must contain a digit");
    }
}
