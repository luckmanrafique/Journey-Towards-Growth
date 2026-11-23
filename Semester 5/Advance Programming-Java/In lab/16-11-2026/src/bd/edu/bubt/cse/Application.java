package bd.edu.bubt.cse;

import java.util.Scanner;
import java.lang.Character;

import bd.edu.bubt.cse.customException.*;
import bd.edu.bubt.cse.util.ValidationHelper;

public class Application {
    public static void main(String[] args){
        boolean isRegistrationSuccessful = true;
        String userName;
        String password;
        Scanner scanner = new Scanner(System.in);

        do{
            String errorMessage="";
            System.out.println("Enter your username:");
            userName = scanner.nextLine();

            System.out.println("Enter your password:");
            password = scanner.nextLine();

            try {
                String firstChar = "";
                if (userName.length() > 0) {
                    firstChar = String.valueOf(userName.charAt(0));
                }

                if (!(ValidationHelper.wordCharacterChecker(firstChar, 'Z', 'A')) &&
                        !(ValidationHelper.wordCharacterChecker(firstChar, 'z', 'a'))) {
                    throw new UsernameFirstLetterException();
                }
            }catch (UsernameFirstLetterException e){
                errorMessage+=e.getMessage()+"\n";
                isRegistrationSuccessful=false;
            }
            try{
                if((ValidationHelper.wordCharacterChecker(userName,' ',' '))){
                    throw new UsernameContainsSpaceException();
                }
            }catch (UsernameContainsSpaceException e){
                errorMessage+=e.getMessage()+"\n";
                isRegistrationSuccessful=false;
            }
            try{
                if(!ValidationHelper.wordLengthChecker(password)){
                    throw new PasswordLengthException();
                }
            }catch (PasswordLengthException e){
                errorMessage+=e.getMessage()+"\n";
                isRegistrationSuccessful=false;
            }
            try{
                if(!ValidationHelper.wordCharacterChecker(password,'Z','A')){
                    throw new PasswordNoCapitalLetterException();
                }

            }catch (PasswordNoCapitalLetterException e){
                errorMessage+=e.getMessage()+"\n";
                isRegistrationSuccessful=false;
            }
            try{
                if(!ValidationHelper.wordCharacterChecker(password,'z','a')){
                    throw new PasswordNoSmallLetterException();
                }
            }catch (PasswordNoSmallLetterException e){
                errorMessage+=e.getMessage()+"\n";
                isRegistrationSuccessful=false;
            }
            try{
                if(!ValidationHelper.wordCharacterChecker(password,'9','0')){
                    throw new PasswordNoDigitException();
                }

            }catch (PasswordNoDigitException e){
                errorMessage+=e.getMessage()+"\n";
                isRegistrationSuccessful=false;
            }
            try{
                if(!ValidationHelper.specialCharacterChecker(password)){
                    throw new PasswordNoSpecialCharacterException();
                }

            }catch (PasswordNoSpecialCharacterException e){
                errorMessage+=e.getMessage()+"\n";
                isRegistrationSuccessful=false;
            }
            if(!isRegistrationSuccessful){
                System.out.println(errorMessage);
            }
        }while(!isRegistrationSuccessful);

        System.out.println("User registration is successful!");
    }
}