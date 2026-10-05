package com.lab;

public class Teacher {
    private String name;
    private  String id;

    public Teacher(){
        this.name=" ";
        this.id=" ";
    }
    public Teacher(String name,String id){
        this.name=name;
        this.id=id;
    }
    public String getId() {return id;}
    public String getName() {return name;}
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    //Association
    void teach(Student student){
        System.out.println("===== Teacher Details =====");
        System.out.println("Name: "+this.name + "ID: "+this.id);
        System.out.println("===== Student Details =====");
        student.show();
    }

}
