package bd.edu.bubt.cse.customException;

public class UsernameContainsSpaceException extends RuntimeException{
    public UsernameContainsSpaceException(){
        super("Invalid Username Exception\n" +
              "Error: Username can't contain space!");
    }
}
