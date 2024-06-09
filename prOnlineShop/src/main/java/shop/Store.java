package shop;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Store {

    private List<Product> products;
    private List<Customer> customers;
    private List<Order> orders;

    public Store(){
        this.products=new ArrayList<>();
        this.customers=new ArrayList<>();
        this.orders=new ArrayList<>();
    }

    public void addProduct(Product product, int addedStock){
        if(product!=null){
            if(products.contains(product)){
                products.set(products.indexOf(product), product);
                product.addStock(addedStock);
            }else{
                products.add(product);
                product.addStock(addedStock);
            }
        }else{
            throw new IllegalArgumentException("The product cannot be null");
        }
    }

    public void removeProduct(Product product, int removedStock){
        if(product!=null){
            if(products.contains(product) && removedStock>0 && removedStock<products.get(products.indexOf(product)).getStockQuantity()){
                products.get(products.indexOf(product)).removeStock(removedStock);
                products.remove(product);
            }else {
                System.err.println("The product specified isn't in the list or the amount of stock to be removed is not valid");
            }
        }else{
            throw new IllegalArgumentException("The product cannot be null");
        }
    }

    public void updateProduct(Product updatedProduct){
        int pos = searchProductByName(updatedProduct.getName());
        if(pos!=-1){
            products.set(products.indexOf(pos), updatedProduct);
        }else{
            throw new NoSuchElementException("The product isnt in the list of added products");
        }
    }

    public int searchProductByName(String name){
        int pos = 0;
        boolean found = false;
        while(pos<products.size()&&!found){
            if(products.get(pos).getName().equalsIgnoreCase(name)){
                found=true;
            }else{
                pos++;
            }
        }
        if(!found){
            return -1;
        }else{
            return pos;
        }
    }

    public Product searchProductbyID(int productID){
        int pos = 0;
        Product aux = new Product(0, "n", "d", 0, 0);
        boolean found = false;
        while(pos<products.size()&&!found){
            if(products.get(pos).getProductId()==productID){
                found=true;
                aux=products.get(pos);
            }else{
                pos++;
            }
        }
        if(!found){
            aux = null;
        }

        return aux;
    }

    public void addCustomer(Customer customer){
        if(customer!=null){
            customers.add(customer);
        }else{
            throw new IllegalArgumentException("Customer cannot be null");
        }
    }

    public void removeCustomer(int customerID){
        Customer customer = getCostumerById(customerID);
        if (customer!=null){
            customers.remove(customer);
        }else{
            throw new NoSuchElementException("Customer with ID "+customerID+" not found");
        }
    }

    public Customer getCostumerById(int customerID) {
        boolean found = false;
        Customer aux = new Customer(0, "n", "email@email.com", "fortnite");
        for(int i = 0; i<customers.size(); i++){
            if(customers.get(i).getCustomerId()==customerID){
                aux = customers.get(i);
                found=true;
            }
        }
        if(!found){
            aux = null;
        }
        return aux;
    }

    public void placeOrder(int customerID, List<Product> productList){
        Customer customer= getCostumerById(customerID);
        if(customer==null){
            throw new NoSuchElementException("Customer with ID "+customerID+" not found");
        }

        int newOrderId = orders.size()+1;
        Order order = new Order(newOrderId, customer);

        for(Product product : productList){
            order.addProduct(product, 1);
        }

        orders.add(order);
        customer.addOrder(order);
    }

    public Order getOrderById(int orderId){
        boolean found = false;
        int pos = 0;
        Customer aux = new Customer(0, "n", "email@email.com", "fortnite");
        Order order = new Order(0, aux);

        while(pos<orders.size() && !found){
            if(orders.get(pos).getOrderId()==orderId){
                found=true;
                order=orders.get(pos);
            }else{
                pos++;
            }
        }

        if(!found){
            order=null;
        }

        return order;
    }

    public List<Order> getOrderHistoryForCustomer(int customerID){
        Customer customer = getCostumerById(customerID);
        if(customer!=null){
            return customer.getOrderHistory();
        }else {
            throw new NoSuchElementException("Customer with ID "+customerID+" not found");
        }
    }

    public void applyDiscountToProduct(int productId, double discount){
        Product product = searchProductbyID(productId);
        if(product!=null){
            double newPrice = product.getPrice()-discount;
            if(newPrice<0){
                throw new IllegalArgumentException("Discount is too large, resulting in a negative and unvalid price");
            }
            product.setPrice(newPrice);
        }else{
            throw new NoSuchElementException("Product with ID "+productId+" not found");
        }
    }

    public List<Product> viewAllProducts(){
        return new ArrayList<>(products);
    }

    public List<Customer> viewAllCustomers(){
        return new ArrayList<>(customers);
    }

    public List<Order> viewAllOrders(){
        return new ArrayList<>(orders);
    }
}