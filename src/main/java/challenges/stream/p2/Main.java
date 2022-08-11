package challenges.stream.p2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product product1=new Product("fasola", 10, 1, Country.POLAND); //1
        Product product2=new Product("Coca-Cola", 7, 6, Country.USA); //6
        Product product3=new Product("Banan", 20, 4, Country.CHINA); //4
        Product product4=new Product("Krem do rąk", 3, 20, Country.JAPAN); //20
        Product product5=new Product("Długopis", 10, 2.5, Country.POLAND); //2.5

        Order order=new Order(new ArrayList<>(List.of(product1, product2, product4, product3)));
        Order order2=new Order(new ArrayList<>(List.of( product2, product4, product3)));
        Order order3=new Order(new ArrayList<>(List.of(product1, product4, product3, product5)));
        Order order4=new Order(new ArrayList<>(List.of(product1,  product4, product3,  product3)));
        Order order5=new Order(new ArrayList<>(List.of(product1, product2, product3, product4)));

        Customer customer=new Customer(new ArrayList<>(List.of(order)), "Jacek", LocalDate.now().minusYears(20));
        Customer customer2=new Customer(new ArrayList<>(List.of(order2, order5, order3)), "Wojtek", LocalDate.now().minusYears(25));
        Customer customer3=new Customer(new ArrayList<>(List.of(order2, order4)), "Krzysiek", LocalDate.now().minusYears(15));
        Customer customer4=new Customer(new ArrayList<>(List.of()), "Kamil", LocalDate.now().minusYears(19));
        Customer customer5=new Customer(new ArrayList<>(List.of(order5)), "Adrian", LocalDate.now().minusYears(40).minusMonths(2));

        Shop shop=new Shop(
                new ArrayList<>(
                        List.of(customer, customer2, customer3, customer4, customer5)
                ),
                List.of(product1, product2, product3, product4, product5));

//        System.out.println(shop.findAverageProductAmountOfAllOrders());
//        System.out.println(shop.getAverageCustomerAge());
//        System.out.println(shop.getOldOrderedItems());
//        System.out.println(shop.getProductWithLeastQuantity());
//        System.out.println(shop.countryOrderMap());
//        System.out.println(shop.birthMonthToCustomerMap());
//        System.out.println(shop.getAdultCustomers());
//        System.out.println(shop.getMostSpendingCustomer());

        System.out.println(shop.birthMonthToCustomerMap());
        System.out.println(shop.countCustomersBornInMonth());

    }
}
