package services;

import data.models.Account;
import dto.request.*;
import dto.response.DepositResponse;
import dto.response.LoginResponse;
import dto.response.RegisterResponse;
import dto.response.TransferResponse;
import utils.*;


import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    RegisterResponse    register(RegisterRequest registerRequest) throws  PhoneNumberExistException;
    LoginResponse login(LoginRequest loginRequest) throws UserLoginWithInvalidCredentialsException;
    DepositResponse depositInto(DepositRequest depositRequest) throws NegativeAmountException;

    void withdrawFrom(WithdrawRequest withdrawRequest);
    TransferResponse transfer(TransferRequest transferRequest) throws NegativeAmountException, InsufficientBalanceException, IncorrectAccountNumberException;

    BigDecimal getBalance(String accountNumber);

    String getCurrentUserLoggedIn(String username);
    List<Account> getAll();

}
