import shop.*;

import java.util.Arrays;
import java.util.List;

public class StoreTest {

    public static void main(String[] args) {
        Store store = new Store();

        // Test Product Creation
        Product product1 = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        Product product2 = new Product(2, "Smartphone", "Latest model smartphone", 799.49, 15);
        Product product3 = new Product(3, "Headphones", "Noise cancelling headphones", 199.99, 20);

        // Test Adding Products
        store.addProduct(product1, 5);
        store.addProduct(product2, 10);
        store.addProduct(product3, 0);

        // Check if products are added correctly
        System.out.println("Products after adding:");
        printProducts(store.viewAllProducts());

        // Test Updating Product
        Product updatedProduct1 = new Product(1, "Laptop", "High performance gaming laptop", 1300.99, 15);
        store.updateProduct(updatedProduct1);

        // Check if product is updated correctly
        System.out.println("Products after updating:");
        printProducts(store.viewAllProducts());

        // Test Removing Products
        store.removeProduct(product1, 5);
        System.out.println("Products after removing some stock:");
        printProducts(store.viewAllProducts());

        // Test Customer Creation
        Customer customer1 = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        Customer customer2 = new Customer(2, "Bob", "bob@example.com", "456 Avenue, City");

        // Test Adding Customers
        store.addCustomer(customer1);
        store.addCustomer(customer2);

        // Check if customers are added correctly
        System.out.println("Customers after adding:");
        printCustomers(store.viewAllCustomers());

        // Test Removing Customer
        store.removeCustomer(2);
        System.out.println("Customers after removing:");
        printCustomers(store.viewAllCustomers());

        // Test Placing Order
        List<Product> orderProducts = Arrays.asList(product1, product2);
        store.placeOrder(1, orderProducts);

        // Check if order is placed correctly
        System.out.println("Orders after placing an order:");
        printOrders(store.viewAllOrders());

        // Test Get Order by ID
        Order order = store.getOrderById(1);
        System.out.println("Order with ID 1:");
        System.out.println(order);

        // Test Get Order History for Customer
        List<Order> orderHistory = store.getOrderHistoryForCustomer(1);
        System.out.println("Order history for customer with ID 1:");
        printOrders(orderHistory);

        // Test Apply Discount to Product
        store.applyDiscountToProduct(3, 50);
        System.out.println("Products after applying discount:");
        printProducts(store.viewAllProducts());
    }

    private static void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void printCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static void printOrders(List<Order> orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
