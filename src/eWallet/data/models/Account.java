package eWallet.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;

import static java.math.BigDecimal.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document
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


    public void withdraw(BigDecimal amount) {
        this.balance = balance.subtract(amount);
    }


}