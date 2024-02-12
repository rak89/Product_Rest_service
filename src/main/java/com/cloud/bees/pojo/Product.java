package com.cloud.bees.pojo;

public class Product {
    private String ProductId;
    private String Name;
    private String Description;
    private double Price;
    private int    quantityAvailable;

    public Product(String productId, String name, String description, double price, int quantityAvailable) {
        ProductId = productId;
        Name = name;
        Description = description;
        Price = price;
        this.quantityAvailable = quantityAvailable;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId='" + ProductId + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Price=" + Price +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
