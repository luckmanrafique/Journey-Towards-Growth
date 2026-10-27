package com.question7;

public class Delivery {
    private double distance;
    private double weight;
    private String membership;
    public Delivery(){
        this.membership=" ";
    }
    public double calculateCharge(double distance){
        this.distance=distance;
        return this.distance*10;
    }

    public double calculateCharge(double distance,double weight){
        this.distance=distance;
        this.weight=weight;
        return (this.distance*10)+(this.weight*5);
    }
    public double calculateCharge(double distance,double weight,boolean premium){
        this.distance=distance;
        this.weight=weight;
        double charge=(this.distance*10)+(this.weight*5);
        if(premium) {
            charge = charge * 0.80;
        }
        return charge;
    }
    public void show(){
        System.out.println("--- Delivery charge calculator ---");
    }
}
