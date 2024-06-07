package shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class Order {

    private int orderId;
    private Customer customer;
    private List<Product> productList;
    private int totalAmount;
    private Date orderDate;

    public Order(int orderId, Customer customer){
        if(orderId<0){
            throw new IllegalArgumentException("The id of the order cannot be lower than 0"); 
        }else{
            this.orderDate=new Date();
            this.orderId=orderId;
            this.customer=customer;
            this.productList=new ArrayList<>();
            this.totalAmount=0;
        }
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product cannot be null and quantity must be greater than 0");
        }
        int pos = searchProductInList(product);
        if (pos != -1) {
            Product existingProduct = productList.get(pos);
            existingProduct.addStock(quantity);
        } else {
            productList.add(product);
        }
        product.removeStock(quantity);
        this.totalAmount += product.getPrice() * quantity;
    }

    public void removeProduct(Product product) {
        int pos = searchProductInList(product);
        if (pos != -1) {
            Product existingProduct = productList.get(pos);
            int quantity = existingProduct.getStockQuantity();
            existingProduct.addStock(quantity);
            productList.remove(pos);
            this.totalAmount -= existingProduct.getPrice() * quantity;
        } else {
            throw new NoSuchElementException("The product asked to remove hasn't been added to the order in the first place");
        }
    }

    private int searchProductInList(Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (product.getName().equalsIgnoreCase(productList.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder shiftyString = new StringBuilder();
        shiftyString.append("Order ID: ").append(orderId)
                .append(", Customer: ").append(customer.getName())
                .append(", Order Date: ").append(orderDate)
                .append(", Products: ").append(productList)
                .append(", Total Amount: ").append(totalAmount);
        return shiftyString.toString();
    }
}
