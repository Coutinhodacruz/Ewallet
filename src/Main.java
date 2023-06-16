import controllers.AccountController;
import controllers.TransactionController;
import dto.request.DepositRequest;
import dto.request.LoginRequest;
import dto.request.RegisterRequest;
import dto.request.TransferRequest;

import javax.swing.*;
import java.math.BigDecimal;

public class Main {
    static AccountController accountController = new AccountController();
    static TransactionController transactionController = new TransactionController();
    static String currentUserLoggedIn = "";

    public static void main(String[] args) {
        startApp();

    }

    private static void exit() {
        System.exit(1);
    }

    private static void startApp() {
        String mainMenu = input("""
                Hi,
                What do you want to do:


                1 -> Register new user
                2 -> Login user
                3 -> Close App
                """);

        switch (mainMenu){
            case "1" -> registerUser();
            case "2" -> loginUser();
            case "3" -> exit();
            default -> startApp();
        }

    }

    private static String input(String message) {
        return JOptionPane.showInputDialog(message);
    }


    private static void registerUser() {
        String firstName = collectStringInput("Enter first name");
        String  lastName = collectStringInput("Enter last name");
        String username = collectStringInput("Enter username");
        String password = collectStringInput("Enter password");
        String email = collectStringInput("Enter email");
        String phoneNumber = collectStringInput("Enter phone Number");
        String dateOfBirth = collectStringInput("Enter Date of Birth");
        RegisterRequest request = new RegisterRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setPhoneNumber(phoneNumber);
        request.setEmailAddress(email);
        request.setUsername(username);
        request.setPassword(password);
        request.setEmailAddress(dateOfBirth);

        display(accountController.registerAccount(request).toString());
        startApp();
    }

    private static void loginUser() {
        LoginRequest loginRequest = new LoginRequest();

        String username = collectStringInput("Enter Username");
        currentUserLoggedIn = username;
        String password = collectStringInput("Enter Password");

        loginRequest.setUsername(username);

        loginRequest.setPassword(password);
        String result = accountController.login(loginRequest).toString();

        if (result == "Invalid credentials")
            startApp();

        else
            displayAppFunctionalities();

    }

    private static void displayAppFunctionalities() {
        String mainMenu = input("""
                
                1 -> Deposit
                2 -> Transfer
                3 -> Balance
                4 -> Main Menu
                """);

        switch (mainMenu){
            case "1" -> deposit();
            case "2" -> transfer();
            case "3" -> getBalance();
            default -> startApp();
        }
    }

    private static void getBalance(){
        String accountNumber = (String) accountController.getAccountNumberWithUsername(currentUserLoggedIn);
        display("Balance: " + accountController.getBalance(accountNumber));
        displayAppFunctionalities();
    }

    private static void transfer() {
        TransferRequest transferRequest = new TransferRequest();

        String amount = collectStringInput("Enter amount");
//        String sender = collectStringInput("Sender AccountNumber");
        String receiver = collectStringInput("Receiver AccountNumber");
        int pin = Integer.parseInt(collectStringInput("Enter your pin"));

        BigDecimal convertedAmount = new BigDecimal(amount);
        transferRequest.setAmount(convertedAmount);
        transferRequest.setSenderAccountNumber(currentUserLoggedIn);
        transferRequest.setRecipientAccountNumber(receiver);
        transferRequest.setPin(pin);
        display(accountController.transfer(transferRequest).toString());
        displayAppFunctionalities();

    }

    private static void deposit(){
        DepositRequest depositRequest = new DepositRequest();

        String accountNumber = collectStringInput("Enter account number");
        String amount = collectStringInput("Enter Amount");
        BigDecimal convertAmount = new BigDecimal(amount);
        depositRequest.setAccountNumber(accountNumber);
        depositRequest.setAmount(convertAmount);
        var result = accountController.deposit(depositRequest);
        display(result.toString());
        displayAppFunctionalities();
    }

    private static void display(String prompt){
        JOptionPane.showMessageDialog(null, prompt);
    }

    private static String collectStringInput(String prompt){
        return JOptionPane.showInputDialog(prompt);
    }




}
