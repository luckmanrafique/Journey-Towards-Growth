package com.lab.hybrid;

public class Application {
    public static void main(){
        AWL awl=new AWL(2,2,3,1500,18.03);
        awl.showDetails();
        awl.fly();
        awl.move();
        awl.brake();
        awl.enlight();
        awl.horn();
    }
}
