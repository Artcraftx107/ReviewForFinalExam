package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Store {

    private List<Product> products;
    private List<Customer> customers;
    private List<Order> orders;

    public Store(){
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addProduct(Product product, int addedStock) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        Product existingProduct = searchProductById(product.getProductId());
        if (existingProduct != null) {
            existingProduct.addStock(addedStock);
        } else {
            product.addStock(addedStock);
            products.add(product);
        }
    }

    public void removeProduct(Product product, int removedStock) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        Product existingProduct = searchProductById(product.getProductId());
        if (existingProduct != null && removedStock > 0 && removedStock <= existingProduct.getStockQuantity()) {
            existingProduct.removeStock(removedStock);
            if (existingProduct.getStockQuantity() == 0) {
                products.remove(existingProduct);
            }
        } else {
            System.err.println("The product specified isn't in the list or the amount of stock to be removed is not valid");
        }
    }

    public void updateProduct(Product updatedProduct) {
        Product existingProduct = searchProductById(updatedProduct.getProductId());
        if (existingProduct != null) {
            products.set(products.indexOf(existingProduct), updatedProduct);
        } else {
            throw new NoSuchElementException("The product isn't in the list of added products");
        }
    }

    public int searchProductByName(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public Product searchProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        if (customer != null) {
            customers.add(customer);
        } else {
            throw new IllegalArgumentException("Customer cannot be null");
        }
    }

    public void removeCustomer(int customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            customers.remove(customer);
        } else {
            throw new NoSuchElementException("Customer with ID " + customerId + " not found");
        }
    }

    public Customer getCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public void placeOrder(int customerId, List<Product> productList) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            throw new NoSuchElementException("Customer with ID " + customerId + " not found");
        }

        int newOrderId = orders.size() + 1;
        Order order = new Order(newOrderId, customer);

        for (Product product : productList) {
            order.addProduct(product, 1);
        }

        orders.add(order);
        customer.addOrder(order);
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrderHistoryForCustomer(int customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            return customer.getOrderHistory();
        } else {
            throw new NoSuchElementException("Customer with ID " + customerId + " not found");
        }
    }

    public void applyDiscountToProduct(int productId, double discount) {
        Product product = searchProductById(productId);
        if (product != null) {
            double newPrice = product.getPrice() - discount;
            if (newPrice < 0) {
                throw new IllegalArgumentException("Discount is too large, resulting in a negative and invalid price");
            }
            product.setPrice(newPrice);
        } else {
            throw new NoSuchElementException("Product with ID " + productId + " not found");
        }
    }

    public List<Product> viewAllProducts() {
        return new ArrayList<>(products);
    }

    public List<Customer> viewAllCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Order> viewAllOrders() {
        return new ArrayList<>(orders);
    }
}