package data.repositories;

import data.models.Account;

import java.util.List;
import java.util.Optional;

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
