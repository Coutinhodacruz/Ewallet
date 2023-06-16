package services;

import data.models.Account;
import data.repositories.AccountRepository;
import data.repositories.AccountRepositoryImpl;
import dto.request.*;
import dto.response.DepositResponse;
import dto.response.LoginResponse;
import dto.response.RegisterResponse;
import dto.response.TransferResponse;
import utils.*;

import java.math.BigDecimal;

import java.util.List;


public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository = new AccountRepositoryImpl();



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


    @Override
    public DepositResponse depositInto(DepositRequest depositRequest) throws NegativeAmountException {
        Account account = accountRepository.findAccount(depositRequest.getAccountNumber());
        BigDecimal amount = depositRequest.getAmount();

        boolean invalidDeposit = validateNegativeAmount(amount);

        if (invalidDeposit)
            throw new NegativeAmountException("Amount cannot be negative");


        account.deposit(amount);
        accountRepository.save(account);
        DepositResponse depositResponse = new DepositResponse("Deposit Successful");
        return depositResponse;
    }

    @Override
    public void withdrawFrom(WithdrawRequest withdrawRequest) {
        Account account = accountRepository.findAccount(withdrawRequest.getAccountNumber());
        BigDecimal amount = withdrawRequest.getAmount();

        account.withdraw(amount);
        accountRepository.save(account);
    }

    private boolean validateNegativeAmount(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) < 0)
            return true;

        return false;

    }



    @Override
    public TransferResponse transfer(TransferRequest transferRequest) throws NegativeAmountException, InsufficientBalanceException, IncorrectAccountNumberException {
        Account accountPin = accountRepository.confirmPin(transferRequest.getPin());

        BigDecimal transferAmount = transferRequest.getAmount();


        BigDecimal senderBalance = getBalance(transferRequest.getSenderAccountNumber());
        if (senderBalance.compareTo(transferAmount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in the sender's account.");
        }

        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAccountNumber(transferRequest.getSenderAccountNumber());
        withdrawRequest.setAmount(transferAmount);
        withdrawFrom(withdrawRequest);


        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber(transferRequest.getRecipientAccountNumber());
        depositRequest.setAmount(transferAmount);


        if (!accountRepository.accountExists(transferRequest.getRecipientAccountNumber())) {
            throw new IncorrectAccountNumberException("Recipient account not found.");
        }
        depositInto(depositRequest);

      depositInto(depositRequest);

        TransferResponse transferResponse = new TransferResponse("Transfer Successful");
        return transferResponse;
    }




    @Override
    public BigDecimal getBalance(String accountNumber) {
        Account account = accountRepository.findAccount(accountNumber);
        return account.getBalance();
    }

    @Override
    public String getCurrentUserLoggedIn(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
