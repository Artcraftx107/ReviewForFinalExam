package shop;

import java.util.Objects;

public class Product {

    private int productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public Product(int id, String n, String d, double p, int numOfProductsAdded){
        if(id<0||p<0){
            throw new IllegalArgumentException("Neither the id or the price can be negative");
        } else if (n.isBlank()) {
            throw new IllegalArgumentException("Name of the product cannot be empty");
        }else{
            this.productId=id;
            this.description=d;
            this.name=n;
            this.price=p;
            this.stockQuantity+=numOfProductsAdded;
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
        this.price = price;
    }

    public void addStock(int numOfProductsAdded){
        if(numOfProductsAdded<0){
            throw new IllegalArgumentException("The number of added stock cannot be less than 0");
        }else{
            this.stockQuantity+=numOfProductsAdded;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && Double.compare(price, product.price) == 0 && stockQuantity == product.stockQuantity && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, description, price, stockQuantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID:").append(productId).append(", Name:").append(name).append(", Description: \n").append(description+"\n").append("Price:").append(price).append(", Current Stock:").append(stockQuantity);
        return ("("+sb+")");
    }
}
