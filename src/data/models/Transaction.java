package data.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class Transaction {


    private String id;
    private TransactionStatus status;
    private TransactionType type;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal amount;
    private LocalDate date = LocalDate.now();

}
