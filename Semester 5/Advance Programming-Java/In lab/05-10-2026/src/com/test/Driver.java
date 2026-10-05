package com.test;

public class Driver {
    public String name;
    public String licenseId;
    public Driver(){
        this.name="";
        this.licenseId="";
    }
    public Driver(String name,String licenseId){
        this.name=name;
        this.licenseId=licenseId;
    }
    public String getName(){return this.name;}
    public String getLicenseId(){return this.licenseId;}
    public void setName(String name){this.name=name;}
    public void setLicenseId(String licenseId){this.licenseId=licenseId;}
    //Association
    public void drive(Car car){
        car.run();
    }
}