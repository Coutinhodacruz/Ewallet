package eWallet.services;


import eWallet.data.models.Account;
import eWallet.dto.request.*;
import eWallet.dto.response.DepositResponse;
import eWallet.dto.response.LoginResponse;
import eWallet.dto.response.RegisterResponse;
import eWallet.dto.response.TransferResponse;
import eWallet.exception.*;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    RegisterResponse register(RegisterRequest registerRequest) throws PhoneNumberExistException;
    LoginResponse login(LoginRequest loginRequest) throws UserLoginWithInvalidCredentialsException;
    DepositResponse depositInto(DepositRequest depositRequest) throws NegativeAmountException;

    void withdrawFrom(WithdrawRequest withdrawRequest);
    TransferResponse transfer(TransferRequest transferRequest) throws NegativeAmountException, InsufficientBalanceException, IncorrectAccountNumberException;

    BigDecimal getBalance(String accountNumber);

    String getCurrentUserLoggedIn(String username);
    List<Account> getAll();

}