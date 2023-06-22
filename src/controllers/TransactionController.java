package controllers;


import dto.response.TransactionResponse;
import services.TransactionService;
import services.TransactionServiceImpl;

import java.util.List;

public class TransactionController {

    TransactionService transactionService = new TransactionServiceImpl();




//    public Object viewTransaction(int accountId) {
//        try {
//            return transactionService.findTransactionsByAccountId(accountId);
//        } catch (Exception exception) {
//            return exception.toString();
//        }
//    }
//        public Object fidTransactionsByAccountId(TransactionResponse transactionResponse){
//        return transactionService.findTransactionsByAccountId(transactionResponse);
//    }

    public Object viewAllDebitTransactions(TransactionResponse transactionResponse){
        return transactionResponse;
    }

    public Object viewALlCreditTransaction(TransactionResponse transactionResponse){
        return transactionResponse;
    }

    public Object findAllTransaction(TransactionResponse transactionResponse){
        return transactionResponse;
    }


}



