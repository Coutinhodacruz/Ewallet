package utils;

import data.models.Account;
import data.models.Transaction;
import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.response.LoginResponse;
import dto.response.RegisterResponse;
import dto.response.TransactionResponse;

public class Mapper {
    public static Account map(RegisterRequest registerRequest) {
        Account account = new Account();
        account.setFirstName(registerRequest.getFirstName());
        account.setLastName(registerRequest.getLastName());
        account.setPhoneNumber(registerRequest.getPhoneNumber());
        account.setEmail(registerRequest.getEmailAddress());
        account.setPassword(registerRequest.getPassword());
        account.setUsername(registerRequest.getUsername());
        account.setPin(registerRequest.getPin());
        return account;
    }

    public static RegisterResponse map(Account account, RegisterResponse registerResponse) {
        registerResponse.setUsername(account.getUsername());
        registerResponse.setAccountNumber(account.getAccountNumber());
        return registerResponse;
    }

    public static Account map(LoginRequest loginRequest){
        Account account = new Account();
        account.setUsername(loginRequest.getUsername());
        account.setPassword(loginRequest.getPassword());
        return account;
    }

    public static LoginResponse map(LoginResponse loginResponse){
        loginResponse.setMessage("Login successful");

        return loginResponse;
    }

    public static TransactionResponse map(Transaction foundTransaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(foundTransaction.getAmount());
        transactionResponse.setTransactionType(foundTransaction.getType());
        transactionResponse.setAccountName(foundTransaction.getAccountName());
        transactionResponse.setDate(foundTransaction.getDate());
        return transactionResponse;
    }


}
