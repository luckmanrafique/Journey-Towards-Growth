package com.exception;
import java.util.Scanner;

public class Application {
    public static int validate(int age) throws InvalidAgeException{
        if(age<18){
            throw new InvalidAgeException("Age is invalid");
        }
        else return age;
    }
    public static void main(String[] args){
        int age;
        Scanner scanner=new Scanner(System.in);
        age= scanner.nextInt();
        try{
            int sanitize=validate(age);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}
