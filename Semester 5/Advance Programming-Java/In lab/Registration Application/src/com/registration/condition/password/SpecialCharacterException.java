package com.registration.condition.password;
public class SpecialCharacterException extends Exception{
    public SpecialCharacterException(){
        super("Password must have a special character");
    }
}
