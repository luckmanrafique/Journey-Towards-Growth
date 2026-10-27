package com.question6;

public class EmailUser {
    private String name;
    public EmailUser(){
        this.name=" ";
    }
    public EmailUser(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void sendEmail(){
        System.out.println("User: "+this.name);
        System.out.println("Sendeing a general email...");
    }
}
