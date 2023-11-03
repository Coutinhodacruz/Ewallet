package eWallet.data.repositories;


import eWallet.data.models.Transaction;
import eWallet.data.models.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionRepositoryImpl implements TransactionRepository {


    private List<Transaction> transactions = new ArrayList<>();
    private int count;


    @Override
    public Transaction save(Transaction transaction) {
        if (transaction.getId() == null)
            transaction.setId(String.valueOf(generateTransactionId()));
        transactions.add(transaction);
        count += 1;
        return transaction;
    }

    @Override
    public Transaction findTransactionById(String id) {
        Transaction foundTransaction = null;
        for (Transaction transaction : transactions){
            boolean idMatch = Objects.equals(transaction.getId(), id);
            if (idMatch) foundTransaction = transaction;
        }
        return foundTransaction;
    }

    @Override
    public List<Transaction> findTransactionsByAccountId(String accountId) {
        List<Transaction> transactionsWithAccountId = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (Objects.equals(transaction.getAccountId(), accountId)) {
                transactionsWithAccountId.add(transaction);
            }
        }
        return transactionsWithAccountId;
    }


    @Override
    public List<Transaction> findAllCreditTransactions(TransactionType creditType) {
        List<Transaction> creditTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType() == creditType) {
                creditTransactions.add(transaction);
            }
        }
        return creditTransactions;
    }

    @Override
    public List<Transaction> findAllDebitTransactions(TransactionType debitType) {
        List<Transaction> debitTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getType() == debitType) {
                debitTransactions.add(transaction);
            }
        }
        return debitTransactions;
    }



    @Override
    public Transaction findByDate(LocalDate date) {
        Transaction foundTransaction = null;
        for (Transaction transaction : transactions)
            if (transaction.getDate().equals(date)) foundTransaction = transaction;


        return foundTransaction;
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return transactions;
    }

    @Override
    public void deleteTransactionById(String id) {
        Transaction foundTransaction = findTransactionById(id);
        transactions.remove(foundTransaction);

    }



    private int generateTransactionId() {
        return count + 1;
    }

    @Override
    public int count() {
        return count;
    }
}