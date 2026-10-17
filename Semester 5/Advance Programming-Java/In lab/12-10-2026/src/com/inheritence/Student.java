package com.inheritence;

public class Student extends UniversityDB{
    private String name;
    private String dept;

    public Student(){super();}
    public Student(int id,String name,String dept){
        super.id=id;
        this.name=name;
        this.dept=dept;
    }

    public String getName(){return this.name;}
    public String getDept(){return this.dept;}

    public void setName(String name){this.name=name;}
    public void setDept(String dept){this.dept=dept;}

    @Override
    public void show(){
        System.out.println("====== Info ======");
        System.out.println("ID: "+this.id+" Name: "+this.name+" Department: "+this.dept);
    }
}
