package bd.edu.bubt.cse.customException;

public class PasswordNoCapitalLetterException extends RuntimeException{
    public PasswordNoCapitalLetterException(){
        super("Invalid Password Exception\n" +
                "Error: Password must contain a capital letter!");
    }
}