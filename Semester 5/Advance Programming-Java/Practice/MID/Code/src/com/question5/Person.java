package com.question5;

public class Person {
    protected String name;
    public Person(){
        this.name=" ";
    }
    public Person(String name){
        this.name=name;
    }
    public void displayInfo(){
        System.out.println("Name: "+this.name);}
}
