package data.repositories;

import data.models.Account;

import java.util.*;

public class AccountRepositoryImpl implements AccountRepository {

    private List<Account> accounts = new ArrayList<>();
    private int count;


    @Override
    public Account save(Account account) {
        if (account.getId() == null)
            account.setId(String.valueOf(generateAccountId()));
        account.setAccountNumber(generateAccountNumber(account.getPhoneNumber()));
        accounts.add(account);
        count += 1;
        return account;
    }

    private String generateAccountNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder(phoneNumber);
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(0);
        }
        return stringBuilder.toString();
    }


//    private String generateAccountNumber(String phoneNumber) {
//        String[] numberArray = new String[10];
//
//        for (int i = 0; i < phoneNumber.length()-1; i++) {
//            numberArray[i] = String.valueOf(phoneNumber.charAt(i + 1));
//
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (int i = 0; i < numberArray.length; i++) {
//            stringBuilder.append(numberArray[i]);
//
//        }
//
//        return stringBuilder.toString();
//    }


    private int generateAccountId() {
        return count + 1;

    }


    @Override
    public Account findById(String id) {
        Account foundAccount = null;
        for (Account account : accounts){
            boolean idMatch = Objects.equals(account.getId(), id);
            if (idMatch) foundAccount = account;
        }
        return foundAccount;
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public void delete(String id) {
        Account foundAccount = findById(id);
        accounts.remove(foundAccount);

    }


    @Override
    public long count() {
        return count;
    }

    @Override
    public Account findAccount(String accountNumber) {
        Account foundAccount = null;
        for (Account account: accounts) {
            if (account.getAccountNumber().equals(accountNumber)) foundAccount = account;
        }
        return foundAccount;
    }

    @Override
    public Account confirmPin(int pin) {
        Account foundPin = null;
        for (Account account: accounts) {
            if (account.getPin() == pin) foundPin = account;
        }
        return foundPin;
    }

    @Override
    public String findByUsername(String username) {
        for (Account account: accounts) {
            if (account.getUsername().equals(username))
                return account.getAccountNumber();
        }
        return null;
    }

    @Override
    public boolean accountExists(String recipientAccountNumber) {
        
        return recipientAccountNumber != null && !recipientAccountNumber.isEmpty();
    }
}
