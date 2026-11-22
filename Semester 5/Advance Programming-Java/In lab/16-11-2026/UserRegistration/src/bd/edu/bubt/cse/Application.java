package bd.edu.bubt.cse;

import java.util.Scanner;

import bd.edu.bubt.cse.util.Validation;

public class Application {
    public static void main(String[] args){
        boolean isRegistrationSuccessful = false;
        String userName;
        String password;
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println("Enter your username:");
            userName = scanner.nextLine();

            System.out.println("Enter your password:");
            password = scanner.nextLine();

            try {
                isRegistrationSuccessful = Validation.validate(userName, password);
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
                System.out.println();
            }

        }while(!isRegistrationSuccessful);

        System.out.println("User registration is successful!");
    }
}
