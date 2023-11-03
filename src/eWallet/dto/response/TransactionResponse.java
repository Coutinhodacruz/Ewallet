package eWallet.dto.response;


import eWallet.data.models.TransactionStatus;
import eWallet.data.models.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

public class TransactionResponse {

    private  String  id;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType;
    private BigDecimal amount = BigDecimal.ZERO;

    private String accountName = "";
    private LocalDateTime transactionDateTime = LocalDateTime.now();
    private LocalDate date = LocalDate.now();

    private  String  message;


}
