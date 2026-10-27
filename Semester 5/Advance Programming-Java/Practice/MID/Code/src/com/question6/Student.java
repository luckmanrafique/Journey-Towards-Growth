package com.question6;

public class Student extends EmailUser{
    public Student(String name){
        super(name);
    }
    @Override
    public void sendEmail(){
        System.out.println("Student is sending email...");
    }
}
