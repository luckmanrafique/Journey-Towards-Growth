package com.lab.interfaces;

public class HybridVehicle implements ILandVehicle,IWaterVehicle{
    private int noOfWheels;
    private int noOfTurbines;
    private int noOfSpins;
    private double wheelRadius;

    public HybridVehicle(){}
    public HybridVehicle(int noOfWheels,int noOfTurbines, int noOfSpins, double wheelRadius ){
        this.noOfWheels=noOfWheels;
        this.noOfTurbines=noOfTurbines;
        this.noOfSpins=noOfSpins;
        this.wheelRadius=wheelRadius;
    }

    public void setNoOfTurbines(int noOfTurbines) {this.noOfTurbines = noOfTurbines;}
    public void setNoOfWheels(int noOfWheels) {this.noOfWheels = noOfWheels;}
    public int getNoOfTurbines() {return noOfTurbines;}
    public int getNoOfWheels() {return noOfWheels;}

    public void setWheelRadius(double wheelRadius) {
        this.wheelRadius = wheelRadius;
    }

    public void showDetails(){
        System.out.println("---------------------------");
        System.out.println("    Hybrid Vehicle Info");
        System.out.println("---------------------------");
        System.out.println("# Number of wheels : "+this.noOfWheels);
        System.out.println("# Number of turbines : "+this.noOfTurbines);
        System.out.println("# Number of Spins : "+this.noOfSpins);
        System.out.println("# Radius of Wheel : "+this.wheelRadius);
        System.out.println("# Completed Distance : "+this.calcDistance()/1000 + " km");
    }
    public void move(){
        System.out.println("The hybrid vehicle is moving using its "+this.noOfWheels+ " wheels and "+this.noOfTurbines +" turbines");
    }
    public void brake(){
        System.out.println("The hybrid vehicle is stopping using its brake");
    }
    public void horn(){
        System.out.println("The hybrid vehicle is making noise using its horn");
    }
    public void enlight(){
        System.out.println("The hybrid vehicle is englighting using its light");
    }
    public double calcDistance(){
        return this.noOfSpins*(2*IVehicle.PI*this.wheelRadius);
    }
}
