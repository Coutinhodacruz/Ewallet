package services;

import data.models.Transaction;
import dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse getTransactionById(int id);
    List<TransactionResponse> getAllTransaction();


}
