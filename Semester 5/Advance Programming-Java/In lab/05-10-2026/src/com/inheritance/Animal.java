package com.inheritance;

public class Animal {
    protected boolean isAlive;
    public Animal(){
    }
    public Animal(boolean isAlive){this.isAlive=isAlive;}
    public void show(){
        System.out.println("The animal is eating");
    }
}
