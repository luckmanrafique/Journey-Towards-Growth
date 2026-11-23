package bd.edu.bubt.cse.customException;

public class PasswordLengthException extends RuntimeException {
    public PasswordLengthException(){
        super("Invalid Password Exception\n" +
                "Error: Password must contain at least 8 character!");
    }
}