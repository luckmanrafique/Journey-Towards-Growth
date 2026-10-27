package com.question5;

public class ScholarshipStudent extends Student{
    private double tuitionFee;
    public ScholarshipStudent(){
        super();
    }
    public ScholarshipStudent(String name,int studentId,double marks,double tuitionFee){
        super(name, studentId, marks);
        this.tuitionFee=tuitionFee;
    }
    public double calculateScholarship(){
        if(this.marks>=80){
            return this.tuitionFee*0.50;
        }
        else if(this.marks>=70 && this.marks<80){
            return this.tuitionFee*0.30;
        }
        else if(this.marks>=60 && this.marks<70){
            return this.tuitionFee*0.20;
        }
        else return 0;
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Tuition fee: "+this.tuitionFee);
    }
}
