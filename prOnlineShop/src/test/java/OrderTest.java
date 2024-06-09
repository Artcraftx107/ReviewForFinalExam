import shop.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testOrderCreation() {
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        Order order = new Order(1, customer);
        assertNotNull(order);
        assertEquals(1, order.getOrderId());
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void testAddProduct() {
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        Order order = new Order(1, customer);
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        order.addProduct(product, 1);
        assertEquals(1, order.getProductList().size());
        assertEquals(product, order.getProductList().get(0));
        assertEquals(1200.99, order.getTotalAmount());
    }
}