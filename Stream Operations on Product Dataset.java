
import java.util.*;
import java.util.stream.Collectors;

public class ProductStreamDemo {
    static class Product {
        String name;
        double price;
        String category;

        Product(String name, double price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("Product{name='%s', price=%.2f, category='%s'}", name, price, category);
        }
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop Pro", 1200.00, "Electronics"),
            new Product("Wireless Mouse", 25.50, "Electronics"),
            new Product("Mechanical Keyboard", 90.00, "Electronics"),
            new Product("Office Chair", 199.99, "Furniture"),
            new Product("Dining Table", 450.00, "Furniture"),
            new Product("Coffee Table", 120.00, "Furniture"),
            new Product("Pen Pack", 4.99, "Stationery"),
            new Product("Notebook", 6.50, "Stationery"),
            new Product("Highlighter Set", 8.99, "Stationery"),
            new Product("Smartphone X", 999.00, "Electronics")
        );

        System.out.println("Products:");
        products.forEach(System.out::println);

        // 1. Group products by category
        Map<String, List<Product>> byCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("\nProducts grouped by category:");
        byCategory.forEach((cat, list) -> {
            System.out.println("Category: " + cat);
            list.forEach(p -> System.out.println("  " + p));
        });

        // 2. Most expensive product in each category
        Map<String, Optional<Product>> mostExpensive = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
            ));

        System.out.println("\nMost expensive product per category:");
        mostExpensive.forEach((cat, opt) -> {
            System.out.println(cat + " -> " + opt.map(Object::toString).orElse("none"));
        });

        // 3. Average price of all products
        Double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(p -> p.price));

        System.out.printf("\nAverage price of all products: %.2f%n", avgPrice);

        // 4. If you want average per category:
        Map<String, Double> avgPerCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category, Collectors.averagingDouble(p -> p.price)));

        System.out.println("\nAverage price per category:");
        avgPerCategory.forEach((cat, avg) -> System.out.printf("  %s -> %.2f%n", cat, avg));
    }
}