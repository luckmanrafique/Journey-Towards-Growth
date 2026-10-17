package com.test;

public class Car {
    private String model;
    private double speed;
    //Aggregation root
    private Engine engine;
    //Composition (ownership) root
    private Key key;
    public Car(){
        this.model="";
        this.key=new Key("Black","Round");
    }
    public Car(String model, double speed,Engine engine,String color,String shape){
        this.model=model;
        this.speed=speed;
        this.engine=engine;
        this.key=new Key(color, shape);

    }
    public String getModel(){return this.model;}
    public double getSpeed(){return this.speed;}
    public void setModel(String model){this.model=model;}
    public void setSpeed(double speed){this.speed=speed;}

    public void run(){
        System.out.println(this.model + " is starting......");
        System.out.println("Bhoom !! Bhoom !! Car is runnig.....with speed"+this.speed);
        //Composition
        this.key.start();
        this.engine.operate();
        System.out.println();
    }
}