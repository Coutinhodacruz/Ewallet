package eWallet.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
public class WithdrawRequest {

    private String pin;
    private String accountNumber;
    private BigDecimal amount;

}
