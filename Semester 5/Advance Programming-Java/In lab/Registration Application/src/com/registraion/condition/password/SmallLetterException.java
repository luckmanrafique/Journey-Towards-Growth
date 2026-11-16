package com.registraion.condition.password;

public class SmallLetterException extends Exception{
    public SmallLetterException(){
        super("Password must contain a small letter");
    }
}
