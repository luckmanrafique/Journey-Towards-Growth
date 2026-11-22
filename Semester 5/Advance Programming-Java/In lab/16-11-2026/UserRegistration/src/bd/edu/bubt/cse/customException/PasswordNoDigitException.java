package bd.edu.bubt.cse.customException;

public class PasswordNoDigitException extends RuntimeException {
    public PasswordNoDigitException(){
        super("Invalid Password Exception\n" +
                "Error: Password must contain a digit!");
    }
}
