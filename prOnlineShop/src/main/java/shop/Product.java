package shop;

import java.util.Objects;

public class Product {

    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public Product(int id, String n, String d, double p, int initStock){
        if(id<0||p<0){
            throw new IllegalArgumentException("Neither the id or the price can be negative");
        } else if (n.isBlank()) {
            throw new IllegalArgumentException("Name of the product cannot be empty");
        } else if (initStock<0) {
            throw new IllegalArgumentException("The number of initial stock cannot be lower than 0");
        } else{
            this.productId=id;
            this.description=d;
            this.name=n;
            this.price=p;
            this.stockQuantity=initStock;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        if(price<0){
            throw new IllegalArgumentException("New price cannot be negative");
        }else{
            this.price = price;
        }
    }

    public void addStock(int numOfProductsAdded){
        if(numOfProductsAdded<0){
            throw new IllegalArgumentException("The number of added stock cannot be less than 0");
        }else{
            this.stockQuantity+=numOfProductsAdded;
        }
    }

    public void removeStock(int numOfProductsRemoved){
        if(numOfProductsRemoved<0){
            throw new IllegalArgumentException("You cannot remove less than 0, use addStock to add more stock");
        } else if (numOfProductsRemoved>stockQuantity) {
            System.err.println("There is not enough stock, the current stock is: "+stockQuantity);
        } else{
            this.stockQuantity-=numOfProductsRemoved;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && name.equalsIgnoreCase(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, description, price, stockQuantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:").append(productId)
                .append(", Name:").append(name)
                .append(", Description: \n").append(description+"\n")
                .append("Price:").append(price)
                .append(", Current Stock:").append(stockQuantity);
        return ("("+sb+")");
    }
}
