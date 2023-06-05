package by.evgen.cardservice.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    public enum Currency {
        INR, USD;
    }

    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private Token token;
}
