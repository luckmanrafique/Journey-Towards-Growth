package com.test;

public class Car {
    private String model;
    private double speed;
    private Engine engine;
    public Car(){
        this.model="";
    }
    public Car(String model, double speed,Engine engine){
        this.model=model;
        this.speed=speed;
        this.engine=engine;
    }
    public String getModel(){return this.model;}
    public double getSpeed(){return this.speed;}
    public void setModel(String model){this.model=model;}
    public void setSpeed(double speed){this.speed=speed;}

    public void run(){
        engine.operate();
        System.out.println(this.model + " is starting......");
        System.out.println("Bhoom !! Bhoom !! Car is runnig.....");
    }
}
