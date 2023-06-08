package services;

import data.models.Account;
import dto.request.DepositRequest;
import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.request.TransferRequest;
import dto.response.DepositResponse;
import dto.response.LoginResponse;
import dto.response.RegisterResponse;
import dto.response.TransferResponse;
import utils.NegativeAmountException;
import utils.PhoneNumberExistException;
import utils.UserLoginWithInvalidCredentialsException;


import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    RegisterResponse register(RegisterRequest registerRequest) throws  PhoneNumberExistException;
    LoginResponse login(LoginRequest loginRequest) throws UserLoginWithInvalidCredentialsException;
    DepositResponse deposit(DepositRequest depositRequest) throws NegativeAmountException;
    TransferResponse transfer(TransferRequest transferRequest);

    BigDecimal getBalance(String accountNumber);
    List<Account> getAll();

}
