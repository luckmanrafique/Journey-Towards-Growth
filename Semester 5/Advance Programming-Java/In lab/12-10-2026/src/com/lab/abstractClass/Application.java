package com.lab.abstractClass;

public class Application {
    public static void main(String[] args){
        //AirVehicle av=new AirVehicle(); error
        AirVehicle av=new Helicopter();
        av.fly();
    }
}
