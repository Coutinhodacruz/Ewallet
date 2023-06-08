package utils;

public class UserRegisterTwiceWithExistingPhoneNumberException extends Throwable {

    public UserRegisterTwiceWithExistingPhoneNumberException(String message){
        super(message);
    }
}
