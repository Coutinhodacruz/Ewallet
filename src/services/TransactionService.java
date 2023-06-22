package services;


import dto.request.TransactionRequest;
import dto.response.TransactionResponse;

import java.util.List;


public interface TransactionService {
    List<TransactionResponse> findAllTransactions();

    TransactionResponse findTransactionsByAccountId(TransactionRequest transactionRequest);

    TransactionResponse viewAllDebitTransactions(TransactionRequest transactionRequest);

    TransactionResponse viewAllCreditTransactions(TransactionRequest transactionRequest);


}
