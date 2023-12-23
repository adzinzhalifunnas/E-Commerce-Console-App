package main.services.thread;

import java.util.UUID;

import main.database.Database;
import main.helper.ProductHelper;
import main.helper.TransactionHelper;
import main.helper.UserHelper;
import main.model.Product;
import main.model.User;
import main.model.request.TransactionRequestDTO;
import main.util.Util;

public class ProcessTransactionThread implements Runnable {
    private UUID productID;
    private UUID addressID;
    private int quantity;
    private double totalPrice;

    public ProcessTransactionThread(UUID productID, UUID addressID, int quantity, double totalPrice) {
        this.productID = productID;
        this.addressID = addressID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    @Override
    public void run() {
        UUID buyerID = Database.loggedInUser.getUserID();
        Product product = ProductHelper.getProduct(productID);
        UUID sellerID = product.getProductSellerID();

        User buyer = UserHelper.getUserDetail(buyerID);
        User seller = UserHelper.getUserDetail(sellerID);

        double sellerBalance = seller.getBalance();
        double newSellerBalance = sellerBalance + totalPrice;

        double userBalance = buyer.getBalance();
        double newBuyerBalance = userBalance - totalPrice;

        int newProductStock = product.getProductStock() - quantity;
        int newProductSold = product.getProductSold() + quantity;

        seller.setBalance(newSellerBalance);
        buyer.setBalance(newBuyerBalance);

        product.setProductStock(newProductStock);
        product.setProductSold(newProductSold);

        String transactionDate = Util.getCurrentDateTime();
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(null, buyerID, sellerID, productID, addressID, quantity, totalPrice, transactionDate, "PAID");
        TransactionHelper.addTransaction(transactionRequestDTO);
    }
}