package shop;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private Customer customer;
    private List<Product> productList;
    private int totalAmount;
    private Date orderDate;

    public Order(int orderId, Customer customer, int totalAmount){
        if(orderId<0){
            throw new IllegalArgumentException("The id of the order cannot be lower than 0"); 
        } else if (totalAmount<0) {
            throw new IllegalArgumentException("The total amount cannot be negative");
        }else{
            this.orderDate=Date.from(Instant.from(LocalDate.now()));
            this.orderId=orderId;
            this.customer=customer;
            this.productList=new ArrayList<>();
            this.totalAmount=totalAmount;
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


}
