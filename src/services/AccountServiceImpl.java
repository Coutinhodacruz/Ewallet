package services;

import data.models.Account;
import data.repositories.AccountRepository;
import data.repositories.AccountRepositoryImpl;
import dto.request.DepositRequest;
import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.request.TransferRequest;
import dto.response.DepositResponse;
import dto.response.LoginResponse;
import dto.response.RegisterResponse;
import dto.response.TransferResponse;
import utils.*;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;


public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository = new AccountRepositoryImpl();

    List<BigDecimal> accountBalances = new ArrayList<>();


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws PhoneNumberExistException {

        boolean phoneNumberExist = validatePhoneNumber(registerRequest.getPhoneNumber());

        if (phoneNumberExist)
            throw new PhoneNumberExistException("Phone number already exist");

        Account account = Mapper.map(registerRequest);
        Account savedAccount = accountRepository.save(account);
        RegisterResponse registerResponse = new RegisterResponse();
        return Mapper.map(savedAccount, registerResponse);


    }
    private boolean validatePhoneNumber(String phoneNumber) {
        List<Account> accountList = accountRepository.findAll();
        for (Account account : accountList) {
            if (account.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
//     return accountList.stream().anyMatch(user -> user.getPhoneNumber().equals(phoneNumber));

    }


//    private boolean isValidPhoneNumber(String phoneNumber) {
//        List<Account> accounts = accountRepository.findAll();
//        for (Account account : accounts){
//            if (account.getAccountNumber().equals(phoneNumber)){
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws UserLoginWithInvalidCredentialsException {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        boolean isAuthenticated = authenticate(username, password);
        if (!isAuthenticated) {
            throw new UserLoginWithInvalidCredentialsException("Invalid credentials");
        }

        // Further processing or response creation can be done here
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Login successful");
        return loginResponse;
    }

    private boolean authenticate(String username, String password) {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


//    @Override
//    public LoginResponse login(LoginRequest loginRequest) throws UserLoginWithInvalidCredentialsException {
//
//        LoginResponse loginResponse = new LoginResponse();
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
//
//        boolean isAuthenticated = authenticate(username, password);
//        if (!isAuthenticated)
//            throw new UserLoginWithInvalidCredentialsException("Invalid Credentials");
//        Account account = Mapper.map(loginRequest);
//        accountRepository.save(account);
//        return Mapper.map(loginResponse);
//    }
//
//
//    private boolean authenticate(String username, String password){
//        List<Account> accounts = accountRepository.findAll();
//        for (Account account : accounts) {
//            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public DepositResponse deposit(DepositRequest depositRequest) throws NegativeAmountException {
        Account account = accountRepository.findAccount(depositRequest.getAccountNumber());
        BigDecimal amount = depositRequest.getAmount();

        boolean invalidDeposit = validateNegativeAmount(amount);

        if (invalidDeposit)
            throw new NegativeAmountException("Amount cannot be negative");


        account.deposit(amount);
        DepositResponse depositResponse = new DepositResponse("Deposit Successful");
        return depositResponse;
    }

    private boolean validateNegativeAmount(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) < 0)
            return true;

        return false;

//        return amount.compareTo(BigDecimal.ZERO) < 0;
    }


//
//    @Override
//    public TransferResponse transfer(TransferRequest transferRequest) throws InsufficientBalanceException, IncorrectAccountNumberException {
//        String fromAccountNumber = transferRequest.getSenderAccountNumber();
//        String toAccountNumber = transferRequest.getRecipientAccountNumber();
//        double amount = transferRequest.getAmount();
//
//        Account fromAccount = accountRepository.findAccount(fromAccountNumber);
//        Account toAccount = accountRepository.findAccount(toAccountNumber);
//
//        if (fromAccount == null || toAccount == null) {
//            throw new IncorrectAccountNumberException("Invalid account number");
//        }
//
//        if (fromAccount.getBalance() < amount) {
//            throw new InsufficientBalanceException("Insufficient balance");
//        }
//
//        fromAccount.withdraw(amount);
//        toAccount.deposit(amount);
//
//        TransferResponse transferResponse = new TransferResponse("Transfer successful");
//        return transferResponse;
//    }


    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
        Account accountPin = accountRepository.confirmPin(transferRequest.getPin());

//        if (account == null || accountPin == null)
//            throw new InvalidAccountException("Invalid details");
        return null;
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        Account account = accountRepository.findAccount(accountNumber);
        return account.getBalance();
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
