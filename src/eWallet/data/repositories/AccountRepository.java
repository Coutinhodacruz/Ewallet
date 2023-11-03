package eWallet.data.repositories;


import eWallet.data.models.Account;

import java.util.List;


public interface AccountRepository {


    Account save(Account account);

    Account findById(String id);

    List<Account> findAll();

    void delete(String id);

    long count();

    Account findAccount(String accountNumber);

    Account confirmPin(int pin);

    String findByUsername(String username);

    boolean accountExists(String recipientAccountNumber);
}