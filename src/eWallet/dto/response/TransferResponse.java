package eWallet.dto.response;

import lombok.Data;

@Data
public class TransferResponse {

    private String message;

    public TransferResponse(String message) {
        this.message = message;
    }
}
