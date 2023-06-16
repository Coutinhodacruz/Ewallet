package services;

import dto.response.TransactionResponse;

import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private List<TransactionResponse> transactionResponseList = new ArrayList<>();

    @Override
    public TransactionResponse getTransactionById(int id) {
        for (TransactionResponse transactionResponse : transactionResponseList){
            if (transactionResponse.getId() == id) return  transactionResponse;
        }
        return null;
    }

    @Override
    public List<TransactionResponse> getAllTransaction() {
        return transactionResponseList;
    }
}
