package com.registration.condition.password;

public class LengthException extends Exception{
    public LengthException(){
        super("Password must be 8 character");
    }
}
