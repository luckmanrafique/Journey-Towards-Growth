package com.inheritence;

public class Application {
    public static void main(String[] args){
        UniversityDB student=new UniversityDB(42);
        Student student2= new Student(81,"Tanvir","CSE");
        student.show();
        student2.show();
    }
}
