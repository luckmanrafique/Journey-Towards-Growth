package com.registraion.condition.password;

public class LengthException extends Exception{
    public LengthException(){
        super("Password must be 8 character");
    }
}
