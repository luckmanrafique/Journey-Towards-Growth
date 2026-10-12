package com.lab.interfaces;

public class Application {
    public static void main(String[] args){
        HybridVehicle hv=new HybridVehicle(4,2,1500,20.3);
        hv.showDetails();
        hv.move();
        hv.brake();
        hv.enlight();
        hv.horn();
    }
}
