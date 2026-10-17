package com.test;

public class Key {
    private  String color;
    private String shape;

    public Key(){}
    public Key(String color,String shape ){
        this.color=color;
        this.shape=shape;
    }
    public String getColor(){return  this.color;}
    public  String getShape(){return  this.shape;}
    void setColor(String color){this.color=color;}
    void setShape(String Shape){this.shape=shape;}
    public void showDetails(){
        System.out.println("===== Key Info =====");
        System.out.println("Color: "+this.color);
        System.out.println("Shape: "+this.shape);
    }
    public void start(){
        System.out.println("The "+this.color);
    }
}
