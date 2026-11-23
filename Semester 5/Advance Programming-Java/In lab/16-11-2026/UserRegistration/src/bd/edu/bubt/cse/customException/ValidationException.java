package bd.edu.bubt.cse.customException;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}