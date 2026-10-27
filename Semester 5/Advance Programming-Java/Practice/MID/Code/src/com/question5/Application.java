package com.question5;

public class Application {
    public static void main(String[] args){
        Person p=new ScholarshipStudent("Daddy",42,93,52845);
        p.displayInfo();
        ScholarshipStudent s= (ScholarshipStudent) p;
        System.out.println("Scholarship amount: "+s.calculateScholarship());
    }
}
