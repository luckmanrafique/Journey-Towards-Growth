package com.question7;

public class Application {
    public static void main(String[] args) {
        Delivery d=new Delivery();
        d.show();
        System.out.println("Distance only: "+d.calculateCharge(5));
        System.out.println("Distance and Weight: "+d.calculateCharge(5,2));
        System.out.println("With membership: "+d.calculateCharge(5,2,true));
    }
}
