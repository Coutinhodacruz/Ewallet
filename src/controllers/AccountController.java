package controllers;

import dto.request.DepositRequest;
import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.request.TransferRequest;
import services.AccountService;
import services.AccountServiceImpl;
import utils.*;

public class AccountController {

    AccountService accountService = new AccountServiceImpl();

    public Object registerAccount(RegisterRequest registerRequest){
        try {
            return accountService.register(registerRequest);
        } catch (PhoneNumberExistException e) {
            return e.getMessage();
        }
    }

    public Object login(LoginRequest loginRequest){
        try {
            return accountService.login(loginRequest);
        } catch (UserLoginWithInvalidCredentialsException e) {
            return e.getMessage();
        }

    }

    public Object transfer(TransferRequest transferRequest){
        try {
            return accountService.transfer(transferRequest);
        } catch (NegativeAmountException | InsufficientBalanceException | IncorrectAccountNumberException e) {
            return e.getMessage();
        }
    }


    public Object deposit(DepositRequest depositRequest){
        try {
            return accountService.depositInto(depositRequest);
        } catch (NegativeAmountException e) {
            return  e.getMessage();
        }
    }

    public Object getBalance(String accountNumber) {
        return accountService.getBalance(accountNumber);
    }

    public Object getAccountNumberWithUsername(String username){
        return accountService.getCurrentUserLoggedIn(username);
    }

}
