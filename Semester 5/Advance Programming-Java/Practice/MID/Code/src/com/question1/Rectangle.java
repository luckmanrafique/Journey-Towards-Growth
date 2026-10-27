package com.question1;

public class Rectangle {
    protected double length;
    protected double breadth;
    public Rectangle(){} //default constructor
    public Rectangle(double length,double breadth){
        this.length=length;
        this.breadth=breadth;
    }
    public double area(){
        return this.length*this.breadth;
    }
}
