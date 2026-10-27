package com.question5;

public class Student extends Person{
    protected int studentId;
    protected double marks;
    public Student(){
        super();
    }
    public Student(String name,int studentId,double marks){
        super(name);
        this.studentId=studentId;
        this.marks=marks;
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Student ID: "+this.studentId);
        System.out.println("Marks: "+this.marks);
    }
}
