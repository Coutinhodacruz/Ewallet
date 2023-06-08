package data.models;


import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

import static java.math.BigDecimal.*;

@Data
public class Account {


    private String id;
    private String firstName;
    private String username;
    private String lastName;
    private String password;
    private String email;
    private String dateOfBirth;
    private String phoneNumber;
    private String address;
    private String accountNumber;
    private int pin;
    private BigDecimal balance = ZERO;



    public void deposit(BigDecimal amount){
        this.balance = balance.add(amount);
    }


}