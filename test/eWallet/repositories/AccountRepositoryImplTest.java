package eWallet.repositories;

import eWallet.data.models.Account;
import eWallet.data.repositories.AccountRepository;
import eWallet.data.repositories.AccountRepositoryImpl;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryImplTest {

    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private Account account = new Account();
    private Account accountTwo = new Account();



    @Test
    void saveOneAccountCountIsOneTest() {
        account.setPhoneNumber("08023677114");
        accountRepository.save(account);
        assertEquals(1, accountRepository.count());
    }


    @Test
    void saveTwoAccountCountIsTwoTest(){
        account.setPhoneNumber("08023677114");
        accountRepository.save(account);

        accountTwo.setPhoneNumber("09023677114");
        accountRepository.save(accountTwo);
        assertEquals(2, accountRepository.count());
    }


    @Test
    void saveOneAccount_AccountIdIsOneTest(){
        account.setPhoneNumber("08023677114");
        Account savedAccount = accountRepository.save(account);
        assertEquals("1", savedAccount.getId());
    }

    @Test
    void savedAccountGetsAccountNumberTest(){
        account.setPhoneNumber("08023677114");
        Account savedAccount = accountRepository.save(account);
        assertEquals("1", savedAccount.getId());
        assertEquals("8023677114", savedAccount.getAccountNumber());
    }

    @Test
    void findAccountByIdTest(){
        account.setPhoneNumber("08023677114");
        accountRepository.save(account);
        Account foundAccount = accountRepository.findById(account.getId());
        assertNotNull(foundAccount);
        assertEquals(account.getId(), foundAccount.getId());


    }

    @Test
    void findAllAccountTest(){
        account.setPhoneNumber("08023677114");
        accountRepository.save(account);

        accountTwo.setPhoneNumber("09023677114");
        accountRepository.save(accountTwo);
        List<Account> foundAllAccount = accountRepository.findAll();
        assertNotNull(foundAllAccount);
        assertEquals(2,foundAllAccount.size());

    }

    @Test
    void deleteAccountTest(){
        account.setPhoneNumber("08023677114");
        accountRepository.save(account);

        accountRepository.delete("08023677114");
        assertNull(accountRepository.findById("08023677114"));

    }


}