package util;
import dao.UserDAO;

import java.util.Scanner;
public class AdminSetup {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        UserDAO userDAO=new UserDAO();
        System.out.println("==================");
        System.out.println(" BUS TICKET SYSTEM SETUP ");
        System.out.println("==================");
        System.out.println("Enter Admin Username: ");
        String username=scanner.nextLine().trim();
        System.out.println("Enter admin full name: ");
        String fullname=scanner.nextLine().trim();
        System.out.println("Enter admin password: ");
        String password=scanner.nextLine();

        if(username.isEmpty()||fullname.isEmpty()||password.length()<8){
            System.out.println("Invalid input!");
            System.out.println("Password must contain at least 8 characters.");
            return;
        }
        if(userDAO.usernameExists(username)){
            System.out.println("Username already exists!");
            return;
        }
        boolean created=userDAO.createUser(
                username,
                password,
                fullname,
                "ADMIN"
        );
        if(created){
            System.out.println("\nAdmin account created successfully!");
        }else{
            System.out.println("\nAdmin account creation failed!");
        }
        scanner.close();
    }
}
