package challenges.io;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Serializer<Product> serializer = new Serializer<>();
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product("Watermelon", 7, 1));
        list.add(new Product("Corn", 10, 50));
        list.add(new Product("lajsd", 50, 20));
        list.add(new Product("lasdjk", 3.75, 100));
        list.add(new Product("lkasdkl", 100, 2));
        list.add(new Product("askdasj", 9.50, 3));
        serializer.writeCSV(list);

    }
}
