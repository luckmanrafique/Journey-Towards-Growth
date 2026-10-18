package com.lab.hybrid;
import com.lab.abstractClass.AirVehicle;
import com.lab.interfaces.IVehicle;
import com.lab.interfaces.IWaterVehicle;
import com.lab.interfaces.ILandVehicle;
public class AWL extends AirVehicle implements IWaterVehicle,ILandVehicle {
    private int noOfTurbines;
    private int noOfWheels;
    private int noOfSpins;
    private double wheelRadius;

    public AWL(){super();}
    public AWL(int noOfPropellers,int noOfTurbines, int noOfWheels,
               int noOfSpins,double wheelRadius){
        super(noOfPropellers);
        this.noOfTurbines=noOfTurbines;
        this.noOfWheels=noOfWheels;
        this.noOfSpins=noOfSpins;
        this.wheelRadius=wheelRadius;
    }

    public void setNoOfPropellers(int noOfPropellers) {super.setNoOfPropellers(noOfPropellers);}
    public void setNoOfTurbines(int noOfTurbines){this.noOfTurbines=noOfTurbines;}
    public void setNoOfWheels(int noOfWheels){this.noOfWheels=noOfWheels;}
    public void setNoOfSpins(int noOfSpins){this.noOfSpins=noOfSpins;}
    public void setWheelRadius(double wheelRadius){this.wheelRadius=wheelRadius;}

    public int getNoOfPropellers() {return super.getNoOfPropellers();}
    public int getNoOfTurbines() {return this.noOfTurbines;}
    public int getNoOfWheels() {return this.noOfWheels;}
    public int getNoOfSpins(){return this.noOfSpins;}
    public double getWheelRadius(){return this.wheelRadius;}

    public void showDetails(){
        System.out.println("======================");
        System.out.println("== AWL Vehicle Info ==");
        System.out.println("======================");
        System.out.println("# Number of propellers: "+this.getNoOfPropellers());
        System.out.println("# Number of Turbines: "+this.noOfTurbines);
        System.out.println("# Number of wheels: "+this.noOfWheels);
        System.out.println("# Number of spins: "+this.noOfSpins);
        System.out.println("# Radius of wheel: "+this.wheelRadius);
        System.out.println("# Completed distance: "+this.calcDistance());
        System.out.println("=======================");
    }
    public void fly() {
        System.out.println("This AWL vehicle is flying with..."+this.getNoOfPropellers()+" number of propellers.");
    }
    public void enlight() {
        System.out.println("This AWL vehicle is enlighting with it's light...");
    }
    public void move(){
        System.out.println("This AWL vehicle can move with either it's "+
                this.getNoOfPropellers()+" propellers, " +
                this.noOfTurbines+" turbines and "+this.noOfWheels+" wheels");
    }
    public void brake(){
        System.out.println("This AWL vehicle is braking with its brake..");
    }
    public void horn(){
        System.out.println("This AWL vehicle is making noise using it's horn.. ");
    }
    public double calcDistance(){
        return this.noOfSpins*(2* IVehicle.PI*this.wheelRadius);
    }
}
