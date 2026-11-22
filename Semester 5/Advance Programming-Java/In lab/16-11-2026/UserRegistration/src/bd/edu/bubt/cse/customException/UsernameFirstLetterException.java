package bd.edu.bubt.cse.customException;

public class UsernameFirstLetterException extends RuntimeException{
    public UsernameFirstLetterException(){
        super("Invalid Username Exception\n" +
              "Error: Username's first letter should be an alphabet!");
    }
}
