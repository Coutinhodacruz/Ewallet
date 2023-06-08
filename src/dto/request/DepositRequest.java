package dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositRequest {

    private String accountNumber;
    private BigDecimal amount;
    private int pin;




}
