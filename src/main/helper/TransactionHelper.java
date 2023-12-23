package main.helper;

import java.util.UUID;

import main.database.Database;
import main.model.Transaction;
import main.model.request.TransactionRequestDTO;
import main.util.FileManagement;
import main.util.Util;

public class TransactionHelper {
    public static void addTransaction(TransactionRequestDTO transactionRequestDTO) {
        Transaction transaction = new Transaction(
            transactionRequestDTO.getTransactionID() == null ? UUID.randomUUID() : transactionRequestDTO.getTransactionID(),
            transactionRequestDTO.getCustomerID(),
            transactionRequestDTO.getSellerID(),
            transactionRequestDTO.getProductID(),
            transactionRequestDTO.getAddressID(),
            transactionRequestDTO.getQuantity(),
            transactionRequestDTO.getTotalPrice(),
            transactionRequestDTO.getTransactionDate(),
            transactionRequestDTO.getTransactionStatus()
        );
        Database.transactions.add(transaction);
        FileManagement.writeToFile("transactions.csv", "transactions", transaction);
    }

    public static void viewBuyerTransactions(UUID buyerID) {
        int no = 0;
        for (Transaction transaction : Database.transactions) {
            if (transaction.getCustomerID().equals(buyerID)) {
                System.out.printf("   - Transaction ID: %s\n", transaction.getTransactionID());
                System.out.printf("   - Product Name: %s\n", ProductHelper.getProductName(transaction.getProductID()));
                System.out.printf("   - Quantity: %d\n", transaction.getQuantity());
                System.out.printf("   - Total Price: %s\n", Util.formatIDR(transaction.getTotalPrice()));
                System.out.printf("   - Transaction Date: %s\n", Util.convertDateTime(transaction.getTransactionDate()));
                System.out.printf("   - Transaction Status: %s\n", transaction.getTransactionStatus());
                System.out.println();
                no++;
            }
        }
        if (no == 0) {
            System.out.println("[Info] You have no transaction yet.");
        }
    }

    public static void viewSellerTransactions(UUID sellerID) {
        int no = 0;
        for (Transaction transaction : Database.transactions) {
            if (transaction.getSellerID().equals(sellerID)) {
                System.out.printf("   - Transaction ID: %s\n", transaction.getTransactionID());
                System.out.printf("   - Product Name: %s\n", ProductHelper.getProductName(transaction.getProductID()));
                System.out.printf("   - Quantity: %d\n", transaction.getQuantity());
                System.out.printf("   - Total Price: %s\n", Util.formatIDR(transaction.getTotalPrice()));
                System.out.printf("   - Transaction Date: %s\n", Util.convertDateTime(transaction.getTransactionDate()));
                System.out.printf("   - Transaction Status: %s\n", transaction.getTransactionStatus());
                System.out.println();
                no++;
            }
        }
        if (no == 0) {
            System.out.println("[Info] You have no transaction yet.");
        }
    }
}
