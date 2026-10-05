package com.inheritance;

public class Human extends Animal {
    private  String color;
    private String gender;

    public Human(){
        super();
    }
    public Human(boolean isAlive,String color,String gender){
        //super(isAlive);
        super.isAlive=isAlive;
        this.color=color;
        this.gender=gender;
    }
}
