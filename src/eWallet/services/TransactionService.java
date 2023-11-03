package eWallet.services;


import eWallet.dto.request.TransactionRequest;
import eWallet.dto.response.TransactionResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TransactionService {
    List<TransactionResponse> findAllTransactions();

    TransactionResponse findTransactionsByAccountId(TransactionRequest transactionRequest);

    TransactionResponse viewAllDebitTransactions(TransactionRequest transactionRequest);

    TransactionResponse viewAllCreditTransactions(TransactionRequest transactionRequest);

    TransactionResponse findAllTransactionByDate(LocalDate date);


}
