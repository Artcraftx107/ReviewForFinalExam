package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String address;
    private List<Order> orderHistory;

    public Customer(int id, String n, String e, String add){
        if(id<0){
            throw new IllegalArgumentException("The id of the customer cannot be less than 0");
        } else if (n.isBlank()||e.isBlank()||add.isBlank()||!isValidEmail(e)) {
            throw new IllegalArgumentException("There has been an error with the input of the name, email or address of the customer");
        }else{
            this.customerId=id;
            this.name=n;
            this.address=add;
            this.email=e;
            this.orderHistory=new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addOrder(Order order){
        if(order!=null){
            orderHistory.add(order);
        }else{
            throw new IllegalArgumentException("Order object cannot be null");
        }
    }

    public void viewOrderHistory(){
        StringBuilder sb = new StringBuilder();
        int cont = 0;
        for(Order order : orderHistory){
            sb.append("Order "+cont+" placed at the date ("+ order.getOrderDate()+"): ")
                    .append(order+"\n");
        }
        System.out.println("Orders from the customer "+name+" with ID("+customerId+"): "+sb);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, email, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
