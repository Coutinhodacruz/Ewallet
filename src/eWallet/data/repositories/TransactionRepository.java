package eWallet.data.repositories;



import eWallet.data.models.Transaction;
import eWallet.data.models.TransactionStatus;
import eWallet.data.models.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Transaction findTransactionById(String id);

    List<Transaction> findTransactionsByAccountId(String accountId);

    List<Transaction> findAllCreditTransactions(TransactionType creditType);

    List<Transaction> findAllDebitTransactions(TransactionType debitType);

    Transaction findByDate(LocalDate date);

    List<Transaction> findAllTransaction();

    void deleteTransactionById(String id);

    int count();

}