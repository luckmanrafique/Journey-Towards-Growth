package com.inheritence;

public class UniversityDB {
    protected int id;
    public UniversityDB(){}
    public UniversityDB(int id){this.id=id;}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    void show(){
        System.out.println("===== Uni ===== ");
        System.out.println("ID: "+this.id);
    }
}
