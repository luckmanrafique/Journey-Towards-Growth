package com.registraion.condition.username;

public class FirstLetterException extends Exception{
    public FirstLetterException(){
        super("User name must start with an alphabet");
    }
}
