package dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {

    private String username;
    private String accountNumber;

    private String message;

    @Override
    public String toString(){
        return String.format("""
                username: %s
                account number: %s
                """, username, accountNumber);
    }



}
