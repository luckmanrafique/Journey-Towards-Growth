package com.lab;

public class Application {
    public static void main(String[] args){
        Teacher teacher1= new Teacher("MA Nur Quraishi","102026");
        Department department=new Department("CSE");
        Student student1=new Student("Tanvir Ahmed Fahim","20255103081",department,"CSE 341");
        teacher1.teach(student1);
    }
}
