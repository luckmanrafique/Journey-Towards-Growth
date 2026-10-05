package com.inheritance;

public class Animal {
    protected boolean isAlive;
    public Animal(){
    }
    public Animal(boolean isAlive){this.isAlive=isAlive;}
    public boolean getIsAlive(){return this.isAlive;}
    public void setIsAlive(boolean isAlive){this.isAlive=isAlive;}
    public void eat(){
        System.out.println("The animal is eating....");
    }
}
