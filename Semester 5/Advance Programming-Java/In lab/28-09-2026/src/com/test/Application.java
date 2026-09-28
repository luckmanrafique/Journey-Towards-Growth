package com.test;

public class Application {
    public static void main(String[] args){
        Driver driver1=new Driver("Tanvir","081");
        Driver driver2=new Driver("Jim","046");
        Engine e=new Engine(4);
        Car car= new Car("BMW BENZ",30,e);
        driver1.drive(car);
        driver2.drive(car);
    }
}
