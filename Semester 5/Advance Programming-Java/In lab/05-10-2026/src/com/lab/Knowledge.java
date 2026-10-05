package com.lab;

public class Knowledge {
    private String subjectName;
    public Knowledge(){this.subjectName=" ";}
    public Knowledge(String subjectName){this.subjectName=subjectName;}
    public String getSubjectName(){return this.subjectName;}
    public void setSubjectName(String subjectName){this.subjectName=subjectName;}

    public void show(){
        System.out.println("Subject name: "+this.subjectName);
    }
}
