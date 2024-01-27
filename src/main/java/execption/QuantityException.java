package execption;

import lombok.Getter;

@Getter
public class QuantityException extends Exception {
    private String customer;
    private String product;
    public QuantityException(String customer, String product) {
        this.customer = customer;
        this.product = product;
    }

    public QuantityException(String message) {
        super(message);
    }

}
