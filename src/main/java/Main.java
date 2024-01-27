import execption.CustomerNotFoundExeception;
import execption.ProductNotFoundExeception;
import execption.QuantityException;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ProductNotFoundExeception, CustomerNotFoundExeception, QuantityException {


        System.out.println("Onlain shop emulation start!");
        System.out.println("Создадим массив покупателей онлайн магазина и выведем список покупателей в консоль:");
        System.out.println("__________________________________________________________________________________");
        OnlineShop.getCustomersList().add(new Customer("Bursaev Vladislav",
                LocalDate.of(1972, 7, 22), "+7-909-898-98-98", Customer.Genders.MALE));
        OnlineShop.getCustomersList().add(new Customer("Bursaeva Zlata",
                LocalDate.of(2014, 7, 27), "+7-909-898-89-89", Customer.Genders.FEMALE));
        printCustomerList();
        System.out.println("__________________________________________________________________________________");

        System.out.println("Создадим массив товаров онлайн магазина и выведем список в консоль:");
        System.out.println("__________________________________________________________________________________");
        OnlineShop.getProductList().add(new Product("appleJuice", 125.00));
        OnlineShop.getProductList().add(new Product("orangeJuice", 125.00));
        OnlineShop.getProductList().add(new Product("tomatoJuice", 125.00));
        OnlineShop.getProductList().add(new Product("grapeJuice", 125.00));
        OnlineShop.getProductList().add(new Product("pineappleJuice", 125.00));
        printProductList();
        System.out.println("__________________________________________________________________________________");

        System.out.println("Сымитируем работу онлайн магазина. Создадим несколько ордеров," +
                "а также проверим отработку исключений при неверно оформленных заказах");
        System.out.println("__________________________________________________________________________________");
        try {
            Order order1 = null;
            order1 = OnlineShop.buyProduct("Bursaev Vladislav", "tomatoJuice", 3);
            OnlineShop.getOrderList().add(order1);
            printOrderList();

            Order order2 = null;
            order2 = OnlineShop.buyProduct("Bursaeva Zlata", "appleJuice", 1);
            OnlineShop.getOrderList().add(order2);
            printOrderList();
/*
            Order order3 = null;
            order3 = OnlineShop.buyProduct("Bursaev Vladislav", "tomatoJuice", -10);
            OnlineShop.getOrderList().add(order3);
            printOrderList();

            Order order4 = null;
            order4 = OnlineShop.buyProduct("Bursaev Vladislav", "tomatoJuice", 121);
            OnlineShop.getOrderList().add(order4);
            printOrderList();

            Order order5 = null;
            order5 = OnlineShop.buyProduct("Petrov Ivan", "tomatoJuice", 3);
            OnlineShop.getOrderList().add(order5);
            printOrderList();

   */
            Order order6 = null;
            order6 = OnlineShop.buyProduct("Bursaev Vladislav", "otherJuice", 1);
            OnlineShop.getOrderList().add(order6);
            printOrderList();

        } catch (QuantityException e) {
            OnlineShop.getOrderList().add(OnlineShop.buyProduct(e.getCustomer(), e.getProduct(), 1));
        } catch (ProductNotFoundExeception e) {
            System.out.println("Product not found");
            System.out.println();
        } catch (CustomerNotFoundExeception e) {
            System.out.println("Customer not found");
            System.out.println();
        } catch (Exception e) {
            throw e;
        }


        System.out.println("Итог:");
        System.out.println("__________________________________________________________________________________");
        printOrderList();
        System.out.println(OnlineShop.getOrderList().size() + " orders received");
        System.out.println("Onlain shop emulation finish!!!");
        System.out.println();


        System.out.println("Проверяем метод, который поздравляет покупателей онлайн магазина с праздниками.");
        System.out.println("__________________________________________________________________________________");
        congratulateTheBuyer(OnlineShop.getCustomersList());
        System.out.println("__________________________________________________________________________________");

    }


    private static void printCustomerList() {
        for (int i = 0; i < OnlineShop.getCustomersList().size(); i++) {
            System.out.println(OnlineShop.getCustomersList().get(i));
        }
    }

    private static void printProductList() {
        for (int i = 0; i < OnlineShop.getProductList().size(); i++) {
            System.out.println(OnlineShop.getProductList().get(i));
        }
    }

    private static void printOrderList() {
        for (int i = 0; i < OnlineShop.getOrderList().size(); i++) {
            System.out.println(OnlineShop.getOrderList().get(i));
        }
    }


    public static void congratulateTheBuyer(List<Customer> customersList) {
        enum Holidays {NOTHING, NEW_YEAR, EIGHT_MARCH, TWENTY_THREE_FEBRUARY}
        ;
        Holidays today = Holidays.NOTHING;

        LocalDate now = LocalDate.now();
        //LocalDate now = LocalDate.of(2024, 2, 23);
        //LocalDate now = LocalDate.of(2024, 3, 8);
        //LocalDate now = LocalDate.of(2024, 12, 31);

        String day = String.valueOf(now.getDayOfMonth());
        String month = String.valueOf(now.getMonth());

        System.out.println("Сегодня: " + day + " " + month);

        if (day.equals("31") && month.equals("DECEMBER")) {
            today = Holidays.NEW_YEAR;
        }
        if (day.equals("23") && month.equals("FEBRUARY")) {
            today = Holidays.TWENTY_THREE_FEBRUARY;
        }
        if (day.equals("8") && month.equals("MARCH")) {
            today = Holidays.EIGHT_MARCH;
        }

        for (int i = 0; i < OnlineShop.getCustomersList().size(); i++) {
            Customer customer = OnlineShop.getCustomersList().get(i);
            Customer.Genders genders = customer.getGenders();

            if (today == Holidays.NOTHING) {
                System.out.println(customer.getFIO() + ", сегодня нет праздника, поэтому просто хорошего дня.");
            } else if (today == Holidays.NEW_YEAR) {
                System.out.println(customer.getFIO() + ", happy New Year!");
            } else if ((genders.equals(Customer.Genders.FEMALE)) && (today == Holidays.EIGHT_MARCH)) {
                System.out.println(customer.getFIO() + ", Congratulations on 8 of MARCH");
            } else if ((genders.equals(Customer.Genders.MALE)) && (today == Holidays.TWENTY_THREE_FEBRUARY)) {
                System.out.println(customer.getFIO() + ", Congratulations on 23 of FEBRUARY");
            }
        }
    }
}
