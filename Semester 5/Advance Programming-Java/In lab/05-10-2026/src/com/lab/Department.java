package com.lab;

public class Department {
    private String name;
    public Department(){this.name=" ";}
    public Department(String name){this.name=name;}

    public String getName() {return name;}
    public void setName(String name){this.name=name;}

    void showDept(){
        System.out.println("Department name: "+this.name);
    }
}
