import shop.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerCreation() {
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        assertNotNull(customer);
        assertEquals(1, customer.getCustomerId());
        assertEquals("Alice", customer.getName());
        assertEquals("alice@example.com", customer.getEmail());
        assertEquals("123 Street, City", customer.getAddress());
    }

    @Test
    void testAddOrder() {
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Street, City");
        Order order = new Order(1, customer);
        customer.addOrder(order);
        assertEquals(1, customer.getOrderHistory().size());
        assertEquals(order, customer.getOrderHistory().get(0));
    }
}