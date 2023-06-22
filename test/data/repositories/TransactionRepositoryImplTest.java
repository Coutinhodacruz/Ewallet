package data.repositories;


import data.models.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static data.models.TransactionStatus.*;
import static data.models.TransactionType.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryImplTest {


    TransactionRepository transactionRepository = new TransactionRepositoryImpl();
     Transaction transaction = new Transaction();
     Transaction transactionTwo = new Transaction();


     @Test
    void saveOneTransactionIsOneTest(){
         transaction.setStatus(SUCCESSFUL);
         transaction.setType(CREDIT);
         transaction.setDate(LocalDate.now());

         transactionRepository.save(transaction);

         assertNotNull(transaction.getId());
         assertEquals(1,transactionRepository.count());
     }


    @Test
    public void saveOneTransaction_TransactionIdIsOneTest() {
        Transaction savedTransaction = new Transaction();
        savedTransaction.setStatus(SUCCESSFUL);
        savedTransaction.setType(CREDIT);
        savedTransaction.setDate(LocalDate.now());
        transactionRepository.save(savedTransaction);

        assertNotNull(savedTransaction);
        assertEquals("1",savedTransaction.getId());

    }

    @Test
    void findTransactionByIdTest(){
         transaction.setAccountNumber("08023677114");
         transactionRepository.save(transaction);
         Transaction foundTransaction = transactionRepository.findTransactionById(transaction.getId());
         assertNotNull(foundTransaction);
         assertEquals(transaction.getId(), foundTransaction.getId());
    }


    @Test
     void findTransactionByTypeTest() {
        transaction.setType(DEBIT);
        transactionRepository.save(transaction);


        transactionTwo.setType(CREDIT);
        transactionRepository.save(transactionTwo);

        List<Transaction> transactions = transactionRepository.findAllDebitTransactions(DEBIT);

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(transaction.getId(), transactions.get(0).getId());
    }

//    @Test
//    void findTransactionByStatusTest(){
//         transaction.setStatus(PENDING);
//         transactionRepository.save(transaction);
//
//         transactionTwo.setStatus(FAILED);
//         transactionRepository.save(transactionTwo);
//         List<Transaction> transactions = transactionRepository.findByStatus(PENDING);
//         assertNotNull(transactions);
//         assertEquals(1,transactions.size());
//         assertEquals(transaction.getId(), transactions.get(0).getId());
//    }

    @Test
    void findTransactionByDateTest(){
         transaction.setStatus(PENDING);
         transaction.setType(DEBIT);
         transaction.setDate(LocalDate.of(2022, 2, 2));
         transactionRepository.save(transaction);

         transactionTwo.setStatus(SUCCESSFUL);
         transactionTwo.setType(CREDIT);
         transactionTwo.setDate(LocalDate.of(2023, 3, 6));
         transactionRepository.save(transactionTwo);

         Transaction foundTransaction = transactionRepository.findByDate(LocalDate.of(2023, 3, 6));
         assertNotNull(foundTransaction);
         assertEquals(transactionTwo.getId(), foundTransaction.getId());

    }

    @Test
     void testFindAll() {

        transactionRepository.save(transaction);
        transactionRepository.save(transactionTwo);

        List<Transaction> transactions = transactionRepository.findAllTransaction();

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertTrue(transactions.contains(transaction));
        assertTrue(transactions.contains(transactionTwo));
    }

    @Test
    public void testDelete() {

      transactionRepository.save(transaction);

       transactionRepository.deleteTransactionById(transaction.getId());

        Transaction deletedTransaction = transactionRepository.findTransactionById(transaction.getId());
        assertNull(deletedTransaction);
    }

}


