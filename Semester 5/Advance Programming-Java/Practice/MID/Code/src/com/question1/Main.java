package com.question1;

public class Main {
    public static void main(String[] args){
        Cuboid[] c=new Cuboid[3];
        c[0]=new Cuboid(5,4,3);
        c[1]=new Cuboid(6,3,2);
        c[2]=new Cuboid(7,2,4);
        for(int i=0;i<3;i++){
            System.out.println("===Cuboid "+(i+1)+"===");
            c[i].show();
            System.out.println("===========");
        }
    }
}