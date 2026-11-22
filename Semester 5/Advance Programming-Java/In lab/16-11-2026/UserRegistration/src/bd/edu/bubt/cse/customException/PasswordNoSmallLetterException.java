package bd.edu.bubt.cse.customException;

public class PasswordNoSmallLetterException extends RuntimeException{
    public PasswordNoSmallLetterException(){
        super("Invalid Password Exception\n" +
                "Error: Password must contain a small letter!");
    }
}
