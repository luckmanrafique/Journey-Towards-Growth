package bd.edu.bubt.cse.util;

import bd.edu.bubt.cse.customException.PasswordLengthException;
import bd.edu.bubt.cse.customException.PasswordNoCapitalLetterException;
import bd.edu.bubt.cse.customException.PasswordNoDigitException;
import bd.edu.bubt.cse.customException.PasswordNoSmallLetterException;
import bd.edu.bubt.cse.customException.PasswordNoSpecialCharacterException;
import bd.edu.bubt.cse.customException.UsernameContainsSpaceException;
import bd.edu.bubt.cse.customException.UsernameFirstLetterException;
import bd.edu.bubt.cse.customException.ValidationException;

public class Validation {
    public static boolean validate(String username, String password) {
        String errors = "";

        errors = validateUsername(username, errors);
        errors = validatePassword(password, errors);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        return true;
    }

    private static String validateUsername(String username, String errors) {
        if (username == null || username.isEmpty()
                || !Character.isLetter(username.charAt(0))) {
            errors = appendError(errors, new UsernameFirstLetterException());
        }
        if (username != null && username.contains(" ")) {
            errors = appendError(errors, new UsernameContainsSpaceException());
        }
        return errors;
    }

    private static String validatePassword(String password, String errors) {
        if (password == null || password.length() < 8) {
            errors = appendError(errors, new PasswordLengthException());
        }
        boolean hasCapitalLetter = false;
        boolean hasSmallLetter = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        if (password != null) {
            for (char character : password.toCharArray()) {
                if (Character.isUpperCase(character)) {
                    hasCapitalLetter = true;
                } else if (Character.isLowerCase(character)) {
                    hasSmallLetter = true;
                } else if (Character.isDigit(character)) {
                    hasDigit = true;
                } else if (!Character.isLetterOrDigit(character)) {
                    hasSpecialCharacter = true;
                }
            }
        }

        if (!hasCapitalLetter) {
            errors = appendError(errors, new PasswordNoCapitalLetterException());
        }
        if (!hasSmallLetter) {
            errors = appendError(errors, new PasswordNoSmallLetterException());
        }
        if (!hasDigit) {
            errors = appendError(errors, new PasswordNoDigitException());
        }
        if (!hasSpecialCharacter) {
            errors = appendError(errors, new PasswordNoSpecialCharacterException());
        }
        return errors;
    }
    private static String appendError(String errors, RuntimeException exception) {
        if (!errors.isEmpty()) {
            errors += "\n\n";
        }
        return errors + exception.getMessage();
    }
}
