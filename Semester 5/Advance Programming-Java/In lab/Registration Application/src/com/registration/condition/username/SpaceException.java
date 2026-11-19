package com.registration.condition.username;

public class SpaceException extends Exception{
    public SpaceException(){
        super("Username must not contain space");
    }
}
