package challenges.stream.p2;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Shop {
    private List<Customer> customers;
    private List<Product> availableProducts;
    public Shop(List<Customer> customers, List<Product> productsAvailable) {
        this.customers = customers;
        this.availableProducts = productsAvailable;
    }

    //zwróć imiona customerów posortowane alfabetycznie+
    public List<String> sortCustomersAlphabetically() {
        return this.customers.stream()
                .map(customer -> customer.getName())
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
    }

    //zwróć średni wiek customerów+
    public double getAverageCustomerAge() {
        return this.customers.stream()
                .mapToDouble(c -> c.getAge())
                .average()
                .orElse(-1);
    }

    //zwróć customerów którzy mają jakieś zamówienia+

    public List<Customer> getCustomersWithOrders() {
        return this.customers.stream()
                .filter(customer -> !customer.getOrders()
                        .isEmpty()
                )
                .toList();
    }

    //zwróć tylko pełnoletnich customerów posortowanych po ich wieku+
    public List<Customer> getAdultCustomers() {
        return this.customers.stream()
                .filter(c -> c.getAge() >= 18)
                .sorted((c1, c2) -> c2.getAge() - c1.getAge())
                .toList();
    }

    public List<Customer> getAdultCustomers2() {
        return this.customers.stream()
                .filter(c -> c.getAge() >= 18)
                .sorted(Comparator.comparingInt(Customer::getAge))
                .toList();
    }


    //zwróć zamówienia nie starsze niż tydzień+
    public List<Order> getNewOrders() {
        // Optional Lambdas:
        // * o.getOrderTime().plusWeeks(1).isAfter(LocalDateTime.now())
        // * LocalDateTime.now().minusDays(7).isBefore(o.getOrderTime())
        // * o.getOrderTime().plusDays(7).isAfter(LocalDateTime.now())

        return this.getAllOrders().stream().filter(o -> LocalDateTime.now().minusWeeks(1).isBefore(o.getOrderTime())).toList();
    }

    //zwróć średnią ilość produktów w zamówieniach+
    public double findAverageProductAmountOfAllOrders() {
        return customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .mapToInt(product -> product.getAmount())
                .average()
                .orElse(-1);
    }
    private List<Order> getAllOrders() {
        return this.customers.stream().map(k -> k.getOrders()).flatMap(Collection::stream).collect(Collectors.toList());
    }


    //zwróć średnią cenę produktów ze wszystkich zamówień+

    public double getAverageProductPrice() {
        return customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getFullPrice)
                .average()
                .orElse(-1);
    }




    //zwróć customera, który złożył wydał najwięcej-

    public Customer getMostSpendingCustomer() {
        // .sorted((c1, c2) -> Double.compare(c2.getSpendings(), c1.getSpendings()))
        // return customers.stream().min(Comparator.comparingDouble(Customer::getSpendings)).orElse(null);
                return customers.stream().sorted(Comparator.comparingDouble(Customer::getSpendings).reversed()).findFirst().orElse(null);
    }





    //zwróć wszystkie produkty zamówione później niż tydzień temu+
    public List<Product> getOldOrderedItems() {
        return this.getAllOrders().stream().filter(o -> LocalDateTime.now().minusWeeks(1).isAfter(o.getOrderTime())).map(k -> k.getProducts()).flatMap(Collection::stream).toList();
    }

    //zwróć produkt którego mamy najmniej (wg. amound)+
    public Product getProductWithLeastQuantity() {
        return this.availableProducts.stream().sorted((c1, c2) -> (int) (c1.getAmount() - c2.getAmount())).findFirst().orElse(null);
    }

    //trudne:


    //zwróć mapę zawierającą kraj oraz ilość produktów pochodzących z tego kraju+
    public Map<String, Integer> countryOrderMap() {
        Map<String, Integer> countryMap = new HashMap<>();
        this.availableProducts.stream().forEach(k -> {
            if(countryMap.get(k.getCountry().name()) != null) {
                countryMap.replace(k.getCountry().name(), countryMap.get(k.getCountry().name()) + 1);
            } else {
                countryMap.put(k.getCountry().name(), 1);
            }
        });
        return countryMap;
    }
    //zwróć mapę której kluczem będzie numer miesiąca a wartością ilość customerów urodzonych w danym miesiącu+

    public Map<Integer, Integer> birthMonthToCustomerMap() {
        Map<Integer, Integer> monthToCustomerMap = new HashMap<>();
        this.customers.stream().forEach(k -> {
            if(monthToCustomerMap.containsKey(k.getBirthday().getMonthValue())) {
                monthToCustomerMap.replace(k.getBirthday().getMonthValue(), monthToCustomerMap.get(k.getBirthday().getMonthValue()) + 1);
            } else {
                monthToCustomerMap.put(k.getBirthday().getMonthValue(), 1);
            }
        });
        return monthToCustomerMap;
    }

    public Map<Integer, Integer> countCustomersBornInMonth() {
        Map<Integer, Integer> result = new HashMap<>();
        return IntStream.range(1, 13)
                .boxed() //zmienia IntStream na Stream<Integer>
                .collect(Collectors.toMap(month -> month, month -> getCustomersBornIn(month).size()));

    }

    private List<Customer> getCustomersBornIn(int month) {
        return customers.stream()
                .filter(customer -> customer.getBirthday().getMonthValue() == month).toList();
    }


}
