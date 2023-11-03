package eWallet.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document
public class Transaction {


    private String accountName = "";

    private String id;
    private TransactionStatus status;
    private TransactionType type;
    private String accountNumber;
    private BigDecimal balance;
    private String accountId;
    private BigDecimal amount;
    private LocalDate date = LocalDate.now();

}
