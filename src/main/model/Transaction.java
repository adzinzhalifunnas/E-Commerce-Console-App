package main.model;

import java.util.UUID;

public class Transaction {
    private UUID transactionID;
    private UUID customerID;
    private UUID sellerID;
    private UUID productID;
    private UUID addressID;
    private int quantity;
    private Double totalPrice;
    private String transactionDate;
    private String transactionStatus;
    
    public Transaction(UUID transactionID, UUID customerID, UUID sellerID, UUID productID, UUID addressID, int quantity, Double totalPrice, String transactionDate, String transactionStatus) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.sellerID = sellerID;
        this.productID = productID;
        this.addressID = addressID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
    }

    public UUID getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(UUID transactionID) {
        this.transactionID = transactionID;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public void setCustomerID(UUID customerID) {
        this.customerID = customerID;
    }

    public UUID getSellerID() {
        return sellerID;
    }

    public void setSellerID(UUID sellerID) {
        this.sellerID = sellerID;
    }

    public UUID getProductID() {
        return productID;
    }

    public void setProductID(UUID productID) {
        this.productID = productID;
    }

    public UUID getAddressID() {
        return addressID;
    }

    public void setAddressID(UUID addressID) {
        this.addressID = addressID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    
}