package com.test;

public class Engine {
    private int numberOfStrok;
    public Engine(){
        this.numberOfStrok=0;
    }
    public Engine(int numberOfStrok){
        this.numberOfStrok=numberOfStrok;
    }

    public int getNumberOfStrok() {
        return this.numberOfStrok;
    }

    public void setNumberOfStrok(int numberOfStrok) {
        this.numberOfStrok = numberOfStrok;
    }
    public void operate(){
        System.out.println("this engine is operating.....");
    }
}