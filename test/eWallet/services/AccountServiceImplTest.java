package eWallet.services;


import eWallet.dto.request.DepositRequest;
import eWallet.dto.request.LoginRequest;
import eWallet.dto.request.RegisterRequest;
import eWallet.dto.request.TransferRequest;
import eWallet.dto.response.LoginResponse;
import eWallet.exception.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceImplTest {

    private AccountService accountService = new AccountServiceImpl();
    private RegisterRequest registerRequest = new RegisterRequest();

    private RegisterRequest registerRequestTwo = new RegisterRequest();




    @BeforeEach
    void setUp(){
        registerRequest.setFirstName("Dominic");
        registerRequest.setLastName("Blessing");
        registerRequest.setUsername("Coutinho");
        registerRequest.setPhoneNumber("07034766551");
        registerRequest.setPin(1111);
        registerRequest.setPassword("Dacruz");

        registerRequestTwo.setFirstName("Legend");
        registerRequestTwo.setLastName("Odogwu");
        registerRequestTwo.setUsername("Ekene");
        registerRequestTwo.setPhoneNumber("09023457689");
        registerRequestTwo.setPin(4190);
        registerRequestTwo.setPassword("Enzo");
    }

    @Test
    void userCanRegisterTest() throws PhoneNumberExistException {
        String expected =
                """
                username: Coutinho
                account number: 7034766551
                """;
        String actual = accountService.register(registerRequest).toString();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void userRegisterTwiceWithExistingPhoneNumberTest() {
        String actual = null;
        try {
            actual = accountService.register(registerRequest).toString();
        } catch (PhoneNumberExistException e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(actual);

        registerRequest.setPhoneNumber("07034766551");
        try {
            accountService.register(registerRequest);
        } catch (PhoneNumberExistException e) {
            System.out.println(e.getMessage());
        }
        assertThrows(PhoneNumberExistException.class, ()->
                accountService.register(registerRequest));

    }


    @Test
    public void loginWithValidCredentialsTest() throws PhoneNumberExistException, UserLoginWithInvalidCredentialsException {
        String actual = accountService.register(registerRequest).toString();
        String expected = """
                username: Coutinho
                account number: 7034766551
                """;
        assertEquals(expected, actual);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Coutinho");
        loginRequest.setPassword("Dacruz");
        LoginResponse loginResponse = accountService.login(loginRequest);

        assertEquals("Login successful", loginResponse.getMessage());
    }




    @Test
    public void userLoginWithInvalidCredentialsTest() {
        try {
            accountService.register(registerRequest);
        } catch (PhoneNumberExistException e) {
            System.out.println(e.getMessage());
        }

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Coutinho");
        loginRequest.setPassword("Dacruz");
        LoginResponse loginResponse = null;

        try {
            loginResponse = accountService.login(loginRequest);
        } catch (UserLoginWithInvalidCredentialsException e) {
            System.out.println(e.getMessage());

            assertEquals("Login successful", loginResponse.getMessage());

            try {
                accountService.login(loginRequest);
            } catch (UserLoginWithInvalidCredentialsException ex) {
                System.out.println(ex.getMessage());
            }
            assertThrows(UserLoginWithInvalidCredentialsException.class,
                    () -> accountService.login(loginRequest));

        }
    }



    @Test @SneakyThrows
    void userCanDepositTest() {
        accountService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Coutinho");
        loginRequest.setPassword("Dacruz");
        LoginResponse loginResponse = accountService.login(loginRequest);

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("7034766551");
        depositRequest.setAmount(BigDecimal.valueOf(10_000));
        depositRequest.setPin(1111);
        accountService.depositInto(depositRequest);
        assertEquals(BigDecimal.valueOf(10_000), accountService.getBalance("7034766551"),loginResponse.getMessage());
    }


    @Test
    void depositNegativeAmountThrowException() throws PhoneNumberExistException {
        accountService.register(registerRequest);
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("7034766551");
        depositRequest.setAmount(BigDecimal.valueOf(-100_00));
        depositRequest.setPin(1111);

        try {
            accountService.depositInto(depositRequest);
        } catch (NegativeAmountException e) {
            System.out.println(e.getMessage());
        }
        assertThrows(NegativeAmountException.class, () ->
                accountService.depositInto(depositRequest));
    }




    @Test
    void userCanTransferFromOneAccountToAnotherTest() throws PhoneNumberExistException, NegativeAmountException {
        accountService.register(registerRequest);

        accountService.register(registerRequestTwo);
        assertEquals(BigDecimal.valueOf(0), accountService.getBalance("9023457689"));

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("7034766551");
        depositRequest.setAmount(BigDecimal.valueOf(3000));
        accountService.depositInto(depositRequest);
        assertEquals(BigDecimal.valueOf(3000), accountService.getBalance("7034766551"));


        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setSenderAccountNumber("7034766551");
        transferRequest.setRecipientAccountNumber("9023457689");
        transferRequest.setAmount(BigDecimal.valueOf(2000));
        transferRequest.setPin(1111);
        try {
            accountService.transfer(transferRequest);
        } catch (InsufficientBalanceException | IncorrectAccountNumberException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(BigDecimal.valueOf(1000), accountService.getBalance("7034766551"));
        assertEquals(BigDecimal.valueOf(2000), accountService.getBalance("9023457689"));
    }



    @Test
    void transferBelowBalanceThrowException() throws PhoneNumberExistException, NegativeAmountException {
        accountService.register(registerRequest);

        accountService.register(registerRequestTwo);
        assertEquals(BigDecimal.valueOf(0), accountService.getBalance("9023457689"));

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("7034766551");
        depositRequest.setAmount(BigDecimal.valueOf(3000));
        accountService.depositInto(depositRequest);
        assertEquals(BigDecimal.valueOf(3000), accountService.getBalance("7034766551"));


        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setSenderAccountNumber("7034766551");
        transferRequest.setRecipientAccountNumber("9023457689");
        transferRequest.setAmount(BigDecimal.valueOf(9000));
        transferRequest.setPin(1111);
        try {
            accountService.transfer(transferRequest);
        } catch (InsufficientBalanceException | IncorrectAccountNumberException e) {
            System.out.println(e.getMessage());
        }


        assertThrows(InsufficientBalanceException.class, () ->
                accountService.transfer(transferRequest)
        );

    }

    @Test
    void transferToIncorrectAccountNumberThrowException() throws PhoneNumberExistException, NegativeAmountException {
        accountService.register(registerRequest);
        accountService.register(registerRequestTwo);
        assertEquals(BigDecimal.valueOf(0), accountService.getBalance("9023457689"));

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("7034766551");
        depositRequest.setAmount(BigDecimal.valueOf(3000));
        accountService.depositInto(depositRequest);
        assertEquals(BigDecimal.valueOf(3000), accountService.getBalance("7034766551"));


//        RegisterRequest recipientRegisterRequest = new RegisterRequest();
//        recipientRegisterRequest.setFirstName("Recipient");
//        recipientRegisterRequest.setPhoneNumber("0987641234677");
//        recipientRegisterRequest.setPin(2222);
//        accountService.register(recipientRegisterRequest);

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setSenderAccountNumber("7034766551");
        transferRequest.setRecipientAccountNumber("9023457689");
        transferRequest.setAmount(BigDecimal.valueOf(2000));
        transferRequest.setPin(1111);

        assertThrows(IncorrectAccountNumberException.class, () ->
                accountService.transfer(transferRequest)
        );
    }

    @Test
    void validateTransferWithIncorrectPinThrowException() throws PhoneNumberExistException, NegativeAmountException {

        accountService.register(registerRequest);
        accountService.register(registerRequestTwo);
        assertEquals(BigDecimal.valueOf(0), accountService.getBalance("9023457689"));

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setAccountNumber("7034766551");
        depositRequest.setAmount(BigDecimal.valueOf(3000));
        accountService.depositInto(depositRequest);
        assertEquals(BigDecimal.valueOf(3000), accountService.getBalance("7034766551"));

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setSenderAccountNumber("7034766551");
        transferRequest.setRecipientAccountNumber("987641234567");
        transferRequest.setAmount(BigDecimal.valueOf(2000));
        transferRequest.setPin(9999); // Incorrect PIN


        assertThrows(IncorrectPinException.class, () ->
                accountService.transfer(transferRequest)
        );
    }


}