package shop;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String address;
    private List<Order> orderHistory;

    public Customer(int id, String n, String e, String add){
        if(id<0){
            throw new IllegalArgumentException("The id of the customer cannot be less than 0");
        } else if (n.isBlank()||e.isBlank()||add.isBlank()||!e.contains("@")) {
            throw new IllegalArgumentException("There has been an error with the input of the name, email or address of the customer");
        }else{
            this.customerId=id;
            this.name=n;
            this.address=add;
            this.email=e;
            this.orderHistory=new ArrayList<>();
        }
    }


}
