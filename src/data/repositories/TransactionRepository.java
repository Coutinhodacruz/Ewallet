package data.repositories;


import data.models.Transaction;
import data.models.TransactionStatus;
import data.models.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Transaction findById(String id);

    List<Transaction> findByStatus(TransactionStatus status);

    List<Transaction> findByType(TransactionType type);

    Transaction findByDate(LocalDate date);

    List<Transaction> findAll();

    void delete(String id);

    int count();
}
