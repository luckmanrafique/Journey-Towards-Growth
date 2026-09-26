package bd.edu.bubt.sms;
import bd.edu.NSU.sms.GradingSystemNSU;

import java.lang.String;
import java.util.Scanner;

public class Application {
    public  static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        String name;
        char gender;
        long student_id;
        short year;
        byte semester;
        float cgpa;
        double semester_fee;
        boolean regular_student;

        System.out.println("Enter your name:");
        name=scanner.nextLine();
        System.out.println("Enter your gender (M/F): ");
        gender=scanner.next().charAt(0);
        System.out.println("Enter you student ID:");
        student_id=scanner.nextLong();
        System.out.println("Enter your year:");
        year=scanner.nextShort();
        System.out.println("Enter your semester:");
        semester= scanner.nextByte();
        System.out.println("Enter you CGPA:");
        cgpa=scanner.nextFloat();
        System.out.println("Enter your semester fee: ");
        semester_fee=scanner.nextDouble();
        System.out.println("Are you a regular student (Ture/False)");
        regular_student=scanner.nextBoolean();

        String[] subjects={"CSE 341","CSE 342","CSE 207","CSE 208","CSE 209","CSE 210" };
        int[] marks=new int[subjects.length];
        for(int i=0;i< subjects.length;i++){
            System.out.println("Enter mark for "+subjects[i]+":");
            marks[i]=scanner.nextInt();
        }

        System.out.println("------ Student Information ------");
        System.out.println("Name: "+name);
        System.out.println("Gender: "+gender);
        System.out.println("Student ID: "+student_id);
        System.out.println("Semester: "+year+"-"+semester);
        System.out.println("CGPA: "+cgpa);
        System.out.println("Semester fee: "+semester_fee);
        System.out.println("Regular Student: "+regular_student);
        System.out.println();
        System.out.println("===== Marks and Grades =====");
        for(int i=0;i< subjects.length;i++){
            String grade_BUBT= GradingSystemBUBT.calculate_grade(marks[i]);
            String grade_NSU= GradingSystemNSU.calculate_grade(marks[i]);
            System.out.println();
            System.out.println("Subject: " + subjects[i]);
            System.out.println("Mark: "+marks[i]);
            System.out.println("BUBT Grade: "+grade_BUBT);
            System.out.println("NSU Grade: "+grade_NSU);
        }
    }
}
