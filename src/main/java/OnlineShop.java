import execption.CustomerNotFoundExeception;
import execption.ProductNotFoundExeception;
import execption.QuantityException;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class OnlineShop {
    @Getter
    private static List<Customer> customersList = new ArrayList<>();
    @Getter
    private static List<Product> productList = new ArrayList<>();
    @Getter
    private static List<Order> orderList = new ArrayList<>();


    public static Order buyProduct(String customerFio, String productName, int quantityProductInOrder)
            throws QuantityException, ProductNotFoundExeception, CustomerNotFoundExeception {
        Order order = null;
        Customer currentCustomer = null;
        for (Customer customer : customersList) {
            if (customer.getFIO().equals(customerFio)) {
                currentCustomer = customer;
                break;
            }
        }

        Product currentProduct = null;
        for (Product product : productList) {
            if (product.getProductName().equals(productName)) {
                currentProduct = product;
                break;
            }
        }

        if (quantityProductInOrder < 0 || quantityProductInOrder > 100) {
            throw new QuantityException(customerFio, productName);
        }
        if (currentProduct == null) {
            throw new ProductNotFoundExeception();
        }
        if (currentCustomer == null) {
            throw new CustomerNotFoundExeception();
        }

        return new Order(currentCustomer, currentProduct, quantityProductInOrder);
    }
}
