package com.lab.abstractClass;

public abstract class AirVehicle {
    private int noOfPropellers;

    public int getNoOfPropellers() {
        return this.noOfPropellers;
    }
    public void setNoOfPropellers(int noOfPropellers) {
        this.noOfPropellers = noOfPropellers;
    }

    public abstract void fly();
}
