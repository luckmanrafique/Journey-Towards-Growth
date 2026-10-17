package com.inheritence;

public class Course extends Student {
    private int course_no;
    private String title;

    public Course (){
        super();
    }
    public Course(int id,int course_no,String title){
        super.id=id;
        this.course_no=course_no;
        this.title=title;
    }


}
