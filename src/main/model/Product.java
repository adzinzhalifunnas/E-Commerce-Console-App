package main.model;

import java.util.UUID;

public class Product {
    private UUID productID;
    private UUID productSellerID;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private int productStock;
    private int productSold;
    
    public Product(UUID productID, UUID productSellerID, String productName, String productDescription, Double productPrice, int productStock, int productSold) {
        this.productID = productID;
        this.productSellerID = productSellerID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productSold = productSold;
    }

    public UUID getProductID() {
        return productID;
    }

    public void setProductID(UUID productID) {
        this.productID = productID;
    }

    public UUID getProductSellerID() {
        return productSellerID;
    }

    public void setProductSellerID(UUID productSellerID) {
        this.productSellerID = productSellerID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getProductSold() {
        return productSold;
    }

    public void setProductSold(int productSold) {
        this.productSold = productSold;
    }

    
}