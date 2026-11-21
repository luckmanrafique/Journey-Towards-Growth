package com.busticket;
import dao.UserDAO;
import model.User;

import java.util.Scanner;

public class LoginTest {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        UserDAO userDAO=new UserDAO();
        System.out.println("====================");
        System.out.println(" Admin LOGIN TEST ");
        System.out.println("=====================");
        System.out.println("Username: ");
        String username=scanner.nextLine();
        System.out.println("Password: " );
        String password=scanner.nextLine();

        User user=userDAO.authenticate(username,password);
        if(user!=null){
            System.out.println("\nLogin Successful!");
            System.out.println("Welcome, "+user.getFullname());
            System.out.println("User ID: "+user.getUserID());
            System.out.println("Role: "+user.getRole());
        }else{
            System.out.println("\nInvalid username or password");
        }
        scanner.close();
    }
}
