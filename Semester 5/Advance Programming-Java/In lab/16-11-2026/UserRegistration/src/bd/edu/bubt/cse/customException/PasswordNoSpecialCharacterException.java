package bd.edu.bubt.cse.customException;

public class PasswordNoSpecialCharacterException extends RuntimeException{
    public PasswordNoSpecialCharacterException(){
        super("Invalid Password Exception\n" +
                "Error: Password must contain a special character!");
    }
}
