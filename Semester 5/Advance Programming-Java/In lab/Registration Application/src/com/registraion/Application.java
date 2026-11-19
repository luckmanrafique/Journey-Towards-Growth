package com.registraion;

import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        String username;
        String password;
        String errors;
        boolean valid;
        do {
            valid = true;
            errors="";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username: ");
            username = scanner.nextLine();
            System.out.println("Enter password: ");
            password = scanner.nextLine();

        }while(!valid);
    }
}
