import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestRunnerOnlineShop {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store();
    }

    @Test
    void testAddProduct() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        store.addProduct(product, 5);
        assertEquals(1, store.viewAllProducts().size());
        assertEquals(product, store.viewAllProducts().get(0));
    }

    @Test
    void testRemoveProduct() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        store.addProduct(product, 5);
        int initialStockQuantity = product.getStockQuantity();
        int quantityToRemove = 3;
        store.removeProduct(product, quantityToRemove);
        assertEquals(initialStockQuantity - quantityToRemove, store.viewAllProducts().get(0).getStockQuantity());
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        store.addCustomer(customer);
        assertEquals(1, store.viewAllCustomers().size());
        assertEquals(customer, store.viewAllCustomers().get(0));
    }

    @Test
    void testRemoveCustomer() {
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        store.addCustomer(customer);
        store.removeCustomer(1);
        assertEquals(0, store.viewAllCustomers().size());
    }

    @Test
    void testPlaceOrder() {
        Product product1 = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        Product product2 = new Product(2, "Smartphone", "Latest model smartphone", 799.49, 15);
        store.addProduct(product1, 5);
        store.addProduct(product2, 10);

        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        store.addCustomer(customer);

        List<Product> orderProducts = Arrays.asList(product1, product2);
        store.placeOrder(1, orderProducts);

        assertEquals(1, store.viewAllOrders().size());
        assertEquals(customer, store.viewAllOrders().get(0).getCustomer());
        assertEquals(2000.48, store.viewAllOrders().get(0).getTotalAmount(), 0.01);
    }

    @Test
    void testApplyDiscountToProduct() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        store.addProduct(product, 5);
        store.applyDiscountToProduct(1, 100);
        assertEquals(1100.99, store.viewAllProducts().get(0).getPrice(), 0.01);
    }
}
