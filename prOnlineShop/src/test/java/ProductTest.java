import shop.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        assertNotNull(product);
        assertEquals(1, product.getProductId());
        assertEquals("Laptop", product.getName());
        assertEquals("High performance laptop", product.getDescription());
        assertEquals(1200.99, product.getPrice());
        assertEquals(10, product.getStockQuantity());
    }

    @Test
    void testAddStock() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        product.addStock(5);
        assertEquals(15, product.getStockQuantity());
    }

    @Test
    void testRemoveStock() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        product.removeStock(5);
        assertEquals(5, product.getStockQuantity());
    }

    @Test
    void testSetPrice() {
        Product product = new Product(1, "Laptop", "High performance laptop", 1200.99, 10);
        product.setPrice(999.99);
        assertEquals(999.99, product.getPrice());
    }
}

