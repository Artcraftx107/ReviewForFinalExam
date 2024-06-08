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
        if(products.contains(updatedProduct)){
            products.set(products.indexOf(updatedProduct), updatedProduct);
        }else{
            throw new NoSuchElementException("The product isnt in the list of added products");
        }
    }

    public int searchProductByName(String name){
        for(int i = 0; i<products.size(); i++){

        }
    }
}
