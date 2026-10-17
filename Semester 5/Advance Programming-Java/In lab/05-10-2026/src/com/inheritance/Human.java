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
    public String getColor(){return this.color;}
    public String getGender(){return this.gender;}
    public void setColor(String color){this.color=color;}
    public void setGender(String gender){this.gender=gender;}

    @Override
    public void eat (){
        System.out.println("The human is eating......");
    }
}
