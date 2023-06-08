package data.repositories;

import data.models.Transaction;
import data.models.TransactionStatus;
import data.models.TransactionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Transaction findById(String id) {
        Transaction foundTransaction = null;
        for (Transaction transaction : transactions){
            boolean idMatch = Objects.equals(transaction.getId(), id);
            if (idMatch) foundTransaction = transaction;
        }
        return foundTransaction;
    }

    @Override
    public List<Transaction> findByStatus(TransactionStatus status) {
       List<Transaction> statusFound = new ArrayList<>();
       for (Transaction transaction : transactions){
           if (transaction.getStatus().equals(status)){
               statusFound.add(transaction);
           }
       }
        return statusFound;
    }

    @Override
    public List<Transaction> findByType(TransactionType type) {
        List <Transaction> typesFound = new ArrayList<>();

        for (Transaction transaction : transactions){
            if (transaction.getType() == type){
                typesFound.add(transaction);
            }
        }

        return typesFound;
    }

    @Override
    public Transaction findByDate(LocalDate date) {
        Transaction foundTransaction = null;
        for (Transaction transaction : transactions)
            if (transaction.getDate().equals(date)) foundTransaction = transaction;



        return foundTransaction;
    }

    @Override
    public List<Transaction> findAll() {
        return transactions;
    }

    @Override
    public void delete(String id) {
        Transaction foundTransaction = findById(id);
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
