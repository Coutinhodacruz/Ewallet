package eWallet.services;

import eWallet.data.models.Transaction;
import eWallet.data.repositories.TransactionRepository;
import eWallet.data.repositories.TransactionRepositoryImpl;
import eWallet.dto.request.TransactionRequest;
import eWallet.dto.response.TransactionResponse;
import eWallet.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {


    private final TransactionRepository transactionRepository  = new TransactionRepositoryImpl();



    @Override
    public TransactionResponse findTransactionsByAccountId(TransactionRequest transactionRequest) {
        List<Transaction> foundTransactions = transactionRepository.findTransactionsByAccountId(transactionRequest.getId());
        return (TransactionResponse) mapTransactions(foundTransactions);
    }

    @Override
    public TransactionResponse viewAllDebitTransactions(TransactionRequest transactionRequest) {
        List<Transaction> debitTransactions = transactionRepository.findAllDebitTransactions(transactionRequest.getTransactionType());
        return (TransactionResponse) mapTransactions(debitTransactions);
    }

    @Override
    public TransactionResponse viewAllCreditTransactions(TransactionRequest transactionRequest) {
        List<Transaction> creditTransactions = transactionRepository.findAllCreditTransactions(transactionRequest.getTransactionType());
        return (TransactionResponse) mapTransactions(creditTransactions);
    }

    @Override
    public List<TransactionResponse> findAllTransactions() {
        List<Transaction> allTransactions = transactionRepository.findAllTransaction();
        return mapTransactions(allTransactions);
    }

    public TransactionResponse findAllTransactionByDate(LocalDate date){
        Transaction foundTransaction = transactionRepository.findByDate(date);
        return (TransactionResponse) mapTransactions(Collections.singletonList(foundTransaction));
    }

    private List<TransactionResponse> mapTransactions(List<Transaction> transactions) {
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionResponse transactionResponse = Mapper.map(transaction);
            transactionResponses.add(transactionResponse);
        }
        return transactionResponses;
    }
}






