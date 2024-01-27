package execption;

public class ProductNotFoundExeception extends Exception{

    public ProductNotFoundExeception() {

    }

    public ProductNotFoundExeception(String message) {
        super(message);
    }
}
