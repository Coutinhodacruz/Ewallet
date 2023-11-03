package eWallet.services;

import eWallet.dto.request.TransactionRequest;
import eWallet.dto.response.TransactionResponse;
import eWallet.services.TransactionService;
import eWallet.services.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionServiceImplTest {

    private TransactionService transactionService;

    private TransactionRequest transactionRequest = new TransactionRequest();



    @BeforeEach
    public void setUp() {
        transactionService = new TransactionServiceImpl();
    }

    @Test
    public void testFindAllTransactions() {
        List<TransactionResponse> transactions = transactionService.findAllTransactions();
        assertTrue(transactions.isEmpty());
    }


    @Test
    void testViewAllDebitTransactions(){
        transactionRequest.setAmount(BigDecimal.valueOf(100));
        transactionRequest.setAmount(BigDecimal.valueOf(400));
        transactionRequest.setAmount(BigDecimal.valueOf(900));

    }
}

//import org.junit.jupiter.api.Assertions;
//        import org.junit.jupiter.api.BeforeEach;
//        import org.junit.jupiter.api.Test;
//
//        import java.util.List;
//
//public class TransactionServiceImplTest {
//
//    private TransactionService transactionService;
//
//    @BeforeEach
//    public void setUp() {
//        transactionService = new TransactionServiceImpl();
//    }
//
//    @Test
//    public void testViewAllDebitTransactions() {
//        // Mock the transaction repository with dummy data
//        TransactionRepository transactionRepository = new TransactionRepositoryMock();
//        ((TransactionServiceImpl) transactionService).setTransactionRepository(transactionRepository);
//
//        // Create dummy debit transactions
//        Transaction debitTransaction1 = new Transaction(1, TransactionType.DEBIT, 100);
//        Transaction debitTransaction2 = new Transaction(2, TransactionType.DEBIT, 200);
//        Transaction debitTransaction3 = new Transaction(3, TransactionType.DEBIT, 300);
//        transactionRepository.addTransaction(debitTransaction1);
//        transactionRepository.addTransaction(debitTransaction2);
//        transactionRepository.addTransaction(debitTransaction3);
//
//        // Call the viewAllDebitTransactions() method
//        List<TransactionResponse> debitTransactions = transactionService.viewAllDebitTransactions(TransactionType.DEBIT);
//
//        // Perform assertions
//        Assertions.assertEquals(3, debitTransactions.size());
//        Assertions.assertTrue(debitTransactions.contains(Mapper.mapToResponse(debitTransaction1)));
//        Assertions.assertTrue(debitTransactions.contains(Mapper.mapToResponse(debitTransaction2)));
//        Assertions.assertTrue(debitTransactions.contains(Mapper.mapToResponse(debitTransaction3)));
//    }
//
//    @Test
//    public void testViewAllCreditTransactions() {
//        // Mock the transaction repository with dummy data
//        TransactionRepository transactionRepository = new TransactionRepositoryMock();
//        ((TransactionServiceImpl) transactionService).setTransactionRepository(transactionRepository);
//
//        // Create dummy credit transactions
//        Transaction creditTransaction1 = new Transaction(4, TransactionType.CREDIT, 400);
//        Transaction creditTransaction2 = new Transaction(5, TransactionType.CREDIT, 500);
//        Transaction creditTransaction3 = new Transaction(6, TransactionType.CREDIT, 600);
//        transactionRepository.addTransaction(creditTransaction1);
//        transactionRepository.addTransaction(creditTransaction2);
//        transactionRepository.addTransaction(creditTransaction3);
//
//        // Call the viewAllCreditTransactions() method
//        List<TransactionResponse> creditTransactions = transactionService.viewAllCreditTransactions(TransactionType.CREDIT);
//
//        // Perform assertions
//        Assertions.assertEquals(3, creditTransactions.size());
//        Assertions.assertTrue(creditTransactions.contains(Mapper.mapToResponse(creditTransaction1)));
//        Assertions.assertTrue(creditTransactions.contains(Mapper.mapToResponse(creditTransaction2)));
//        Assertions.assertTrue(creditTransactions.contains(Mapper.mapToResponse(creditTransaction3)));
//    }
//}


