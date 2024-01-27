package execption;

public class CustomerNotFoundExeception extends Exception{
    public CustomerNotFoundExeception() {
    }

    public CustomerNotFoundExeception(String message) {
         super(message);
    }
}
