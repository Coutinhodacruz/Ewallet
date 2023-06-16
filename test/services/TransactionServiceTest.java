package services;

import data.models.Transaction;
import dto.response.TransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TransactionServiceTest {
    private TransactionService transactionService = new TransactionServiceImpl();

    @BeforeEach
    public void setUp() {
        transactionService = new TransactionServiceImpl();


    }

//    @Test
//    public void testGetTransactionById() {
//        Transaction transaction = new Transaction();
//
//        transactionService.addTransaction(transaction);
//        TransactionResponse retrievedTransaction = transactionService.getTransactionById(1);
//
//
//        Assertions.assertEquals(transaction.getId(), retrievedTransaction.getId());
//        Assertions.assertEquals(transaction.getCustomerName(), retrievedTransaction.getCustomerName());
//        Assertions.assertEquals(transaction.getAmount(), retrievedTransaction.getAmount());
//
//    }

    @Test
    public void testGetAllTransaction() {
        List<TransactionResponse> allTransactions = transactionService.getAllTransaction();
        Assertions.assertEquals(3, allTransactions.size());

    }
}
