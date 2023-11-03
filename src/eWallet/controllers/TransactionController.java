package eWallet.controllers;


import eWallet.dto.request.TransactionRequest;
import eWallet.services.TransactionService;
import eWallet.services.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1")
public class TransactionController {

    TransactionService transactionService = new TransactionServiceImpl();




    public Object fidTransactionsByAccountId(TransactionRequest transactionRequest){
        return transactionService.findTransactionsByAccountId(transactionRequest);
    }

    public Object viewAllDebitTransactions(TransactionRequest transactionRequest){
        return transactionService.viewAllDebitTransactions(transactionRequest);
    }

    public Object viewALlCreditTransaction(TransactionRequest transactionRequest){
        return transactionService.viewAllCreditTransactions(transactionRequest);
    }

    public Object findAllTransaction(){
        return transactionService.findAllTransactions();
    }

    public Object findAllTransactionByDate(LocalDate date){

        return transactionService.findAllTransactionByDate(date);
    }

    public Object viewTransaction(TransactionRequest accountId) {
        try {
            return transactionService.findTransactionsByAccountId(accountId);
        } catch (Exception exception) {
            return exception.toString();
        }
    }


    public Object getAllTransaction(String allTransactions) {
        return transactionService.findAllTransactions();
    }
}



