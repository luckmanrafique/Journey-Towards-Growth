package com.academy;

public class Athlete {
    private String athleteName;
    private int athleteId;
    private double performanceScore;

    public Athlete(){
        this.athleteName=" ";
    }
    public Athlete(String name,int id,double score){
        this.athleteName=name;
        this.athleteId=id;
        this.performanceScore=score;
    }
    public void setAthleteName(String name){
        this.athleteName=name;
    }
    public void setAthleteId(int id){this.athleteId=id;}
    public void setPerformanceScore(double score){
        if(score>=0 && score<=100) this.performanceScore=score;
        else System.out.println("Invalid Score");
    }
    public String getAthleteName(){return  this.athleteName;}
    public int getAthleteId(){return this.athleteId;}
    public double getPerformanceScore(){return this.performanceScore;}

    public String calculatePerformanceLevel(){
        if(performanceScore>=80) return "Elite";
        else if(performanceScore>=60) return "Advanced";
        else if(performanceScore>=40) return "Intermediate";
        else return "Beginner";
    }
    public void display(){
        System.out.println("======== Athlete INFO =======");
        System.out.println("Name: "+ getAthleteName());
        System.out.println("ID: "+getAthleteId());
        System.out.println("Score: "+getPerformanceScore());
        System.out.println("Level: "+calculatePerformanceLevel());
    }
}
