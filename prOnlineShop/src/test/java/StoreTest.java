import shop.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

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
        assertEquals(15, store.viewAllProducts().get(0).getStockQuantity());
    }

    @Test
    void testRemoveProduct() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        store.addProduct(product, 5);
        store.removeProduct(product, 5);
        assertEquals(10, store.viewAllProducts().get(0).getStockQuantity());
        store.removeProduct(product, 10);
        assertEquals(0, store.viewAllProducts().size());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        store.addProduct(product, 5);
        Product updatedProduct = new Product(1, "Laptop", "High performance gaming laptop", 1300.99, 15);
        store.updateProduct(updatedProduct);
        assertEquals("High performance gaming laptop", store.viewAllProducts().get(0).getDescription());
        assertEquals(1300.99, store.viewAllProducts().get(0).getPrice());
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        store.addCustomer(customer);
        assertEquals(1, store.viewAllCustomers().size());
        assertEquals("Alice", store.viewAllCustomers().get(0).getName());
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

        store.placeOrder(1, Arrays.asList(product1, product2));

        assertEquals(1, store.viewAllOrders().size());
        assertEquals(2, store.viewAllOrders().get(0).getProductList().size());
    }

    @Test
    void testGetOrderById() {
        Product product1 = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        Product product2 = new Product(2, "Smartphone", "Latest model smartphone", 799.49, 15);
        store.addProduct(product1, 5);
        store.addProduct(product2, 10);

        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        store.addCustomer(customer);

        store.placeOrder(1, Arrays.asList(product1, product2));

        Order order = store.getOrderById(1);
        assertNotNull(order);
        assertEquals(1, order.getOrderId());
        assertEquals("Alice", order.getCustomer().getName());
    }

    @Test
    void testGetOrderHistoryForCustomer() {
        Product product1 = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        Product product2 = new Product(2, "Smartphone", "Latest model smartphone", 799.49, 15);
        store.addProduct(product1, 5);
        store.addProduct(product2, 10);

        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        store.addCustomer(customer);

        store.placeOrder(1, Arrays.asList(product1, product2));

        assertEquals(1, store.getOrderHistoryForCustomer(1).size());
        assertEquals(2, store.getOrderHistoryForCustomer(1).get(0).getProductList().size());
    }

    @Test
    void testApplyDiscountToProduct() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        store.addProduct(product, 5);
        store.applyDiscountToProduct(1, 200.00);
        assertEquals(1000.99, store.viewAllProducts().get(0).getPrice());
    }
}