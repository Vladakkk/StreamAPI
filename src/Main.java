import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product.ProductBuilder()
                .type("Book").price(20)
                .discount(false)
                .createDate(new Date(124, Calendar.MAY, 16))
                .build());
        products.add(new Product.ProductBuilder()
                .type("Book").price(36)
                .discount(true)
                .createDate(new Date(124, Calendar.APRIL, 21))
                .build());
        products.add(new Product.ProductBuilder()
                .type("Toy").price(70)
                .discount(false)
                .createDate(new Date(124, Calendar.JULY, 2))
                .build());
        products.add(new Product.ProductBuilder()
                .type("Book").price(54)
                .discount(true)
                .createDate(new Date(124, Calendar.FEBRUARY, 12))
                .build());
        products.add(new Product.ProductBuilder()
                .type("Toy").price(86)
                .discount(false)
                .createDate(new Date(124, Calendar.JUNE, 6))
                .build());
        products.add(new Product.ProductBuilder()
                .type("Book").price(42)
                .discount(true)
                .createDate(new Date(124, Calendar.JANUARY, 27))
                .build());
        products.add(new Product.ProductBuilder()
                .type("Toy").price(98)
                .discount(true)
                .createDate(new Date(124, Calendar.MAY, 5))
                .build());

        List<Product> expensiveBooks = products.stream()
                .filter(product -> product.getType()
                        .equals("Book") && product.getPrice() > 35)
                .toList();
        System.out.println("1) Expensive books: ");
        expensiveBooks.forEach(System.out::println);

        List<Product> discountedBooks = products.stream()
                .filter(product -> product.getType()
                        .equals("Book") && product.hasDiscount())
                .map(product -> new Product.ProductBuilder()
                        .type(product.getType())
                        .price(product.getPrice() * 0.9).build())
                .toList();

        System.out.println("\n2) Discounted books:");
        discountedBooks.forEach(System.out::println);

        Optional<Product> cheapestBook = products.stream()
                .filter(product -> product.getType()
                        .equals("Book"))
                .min(Comparator.comparingDouble(Product::getPrice));

        cheapestBook.ifPresentOrElse(
                product -> System.out.println("\n3) Cheapest book: " + product),
                () -> {throw new NoSuchElementException
                        ("Product [category: Book] not found");});

        List<Product> lastAddedProducts = products.stream()
                .sorted(Comparator.comparing(Product::getCreateDate).reversed())
                .limit(2)
                .toList();

        System.out.println("\n4) Last added products:");
        lastAddedProducts.forEach(System.out::println);

        double totalCost = products.stream()
                .filter(product -> product.getCreateDate()
                        .getYear() == new Date().getYear())
                .filter(product -> product.getType().equals("Book"))
                .filter(product -> product.getPrice() <= 75)
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("\n6) Total cost of all books, added this year and not more expensive than 75$: " + totalCost);

        Map<String, List<Product>> groupedProducts = products.stream()
                .collect(Collectors.groupingBy(Product::getType));

        System.out.println("\n7) Grouping by product type:");
        groupedProducts.forEach((type, productList) -> {
            System.out.println(type + ":");
            productList.forEach(System.out::println);
        });

    }
}