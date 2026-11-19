package com.registraion.condition.password;

public class CapitalLetterException extends Exception{
    public CapitalLetterException(){
        super("Password must contain a capital letter");
    }
}
