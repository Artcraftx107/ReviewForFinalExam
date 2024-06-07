package shop;

import java.util.Date;
import java.util.List;

public class Order {

    private int orderId;
    private Customer customer;
    private List<Product> productList;
    private int totalAmount;
    private Date orderDate;

    public Order(int orderId, Customer customer, int totalAmount, Date orderDate){
        if(orderId<0){
            throw new IllegalArgumentException("The id of the order cannot be lower than 0"); 
        } else if (totalAmount<0) {
            
        }
    }

}
