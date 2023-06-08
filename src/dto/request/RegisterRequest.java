package dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private String dateOfBirth;
    private int pin;

}
