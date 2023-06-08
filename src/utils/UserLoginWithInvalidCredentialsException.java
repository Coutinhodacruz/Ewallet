package utils;

public class UserLoginWithInvalidCredentialsException extends Throwable{

    public UserLoginWithInvalidCredentialsException(String message){
        super(message);
    }
}
