package com.registration.condition.password;

public class CapitalLetterException extends Exception{
    public CapitalLetterException(){
        super("Password must contain a capital letter");
    }
}
