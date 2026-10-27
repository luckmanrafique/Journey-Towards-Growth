package com.question1;

public class Cuboid extends Rectangle {
    private double height;
    public Cuboid(){ //Default constructor using super to initialize parent constructor also
        super();
    }
    public Cuboid(double length,double breadth,double height){
        super(length,breadth);
        this.height=height;
    }
    public double volume(){
        return area()*this.height;
    }
    public void show(){
        System.out.println("Length: "+this.length);
        System.out.println("Breadth: "+this.breadth);
        System.out.println("Height: "+this.height);
        System.out.println("Area: "+area());
        System.out.println("Volume: "+volume());
    }
}
