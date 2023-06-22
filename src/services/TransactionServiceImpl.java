package services;

import data.models.Transaction;
import data.repositories.TransactionRepository;
import data.repositories.TransactionRepositoryImpl;
import dto.request.TransactionRequest;
import dto.response.TransactionResponse;
import utils.Mapper;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();



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

    private List<TransactionResponse> mapTransactions(List<Transaction> transactions) {
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionResponse transactionResponse = Mapper.map(transaction);
            transactionResponses.add(transactionResponse);
        }
        return transactionResponses;
    }
}






