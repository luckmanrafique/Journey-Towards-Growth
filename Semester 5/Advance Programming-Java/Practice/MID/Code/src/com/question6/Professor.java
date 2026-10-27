package com.question6;

public class Professor extends EmailUser{
    public Professor(String name){
        super(name);
    }
    @Override
    public void sendEmail(){
        System.out.println(this.getName()+" is sending an official mail...");
    }
}
