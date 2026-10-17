package com.lab;

public class Student {
    private String name;
    private  String id;
    //Aggregation root
    private Department department;
    //Composition root
    private Knowledge knowledge;
    public Student(){
        this.name=" ";
        this.id=" ";
    }
    public Student(String name,String id,Department department,String subjectName){
        this.name=name;
        this.id=id;
        this.department=department;
        this.knowledge=new Knowledge(subjectName);
    }
    public String getName(){return this.name;}
    public String getId(){return this.id;}
    public void setName(String name){this.name=name;}
    public void setId(String id){this.id=id;}

    public void show(){
        System.out.println("Student name: "+this.name + " Student ID: "+this.id);
        department.showDept();
    }
}
