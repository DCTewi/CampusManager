package Service;

import java.util.regex.Pattern;

public class ValidationService
{
    private static final String emailValidator = "^[\\w\\-._]{2,}@[\\w\\-_]{2,}\\.[a-zA-Z]{2,}$";
    private static final String userNameValidator = "^.{3,30}$";
    private static final String phoneValidator = "^1[0-9]{10}$";
    private static final String passwordValidator = "^[a-zA-Z0-9_.!]{6,20}$";
    private static final String urlValidator = "^https?://.*$";

    public static boolean validateEmail(String str)
    {
        return Pattern.matches(emailValidator, str);
    }

    public static boolean validateUserName(String str)
    {
        return Pattern.matches(userNameValidator, str);
    }

    public static boolean validatePhone(String str)
    {
        return Pattern.matches(phoneValidator, str);
    }

    public static boolean validatePassword(String str)
    {
        return Pattern.matches(passwordValidator, str);
    }

    public static boolean validateUrl(String str)
    {
        return Pattern.matches(urlValidator, str);
    }
}
