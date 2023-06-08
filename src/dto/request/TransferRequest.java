package dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {

    private String senderAccountNumber;
    private String recipientAccountNumber;
    private String amount;
    private int pin;
    private String description;
}
