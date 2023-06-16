package dto.response;


import lombok.*;

@Getter
@Setter


public class LoginResponse {
    private String message;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginResponse{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
