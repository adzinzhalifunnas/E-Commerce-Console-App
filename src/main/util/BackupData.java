package main.util;

import main.database.Database;
import main.model.Address;
import main.model.Product;
import main.model.Transaction;
import main.model.User;

public class BackupData {
    public static void backupData() {
        System.out.println("[Info] Backing up data...");
        FileManagement.clearFile("transactions.csv");
        FileManagement.clearFile("users.csv");
        FileManagement.clearFile("products.csv");
        FileManagement.clearFile("addresses.csv");
        for (Transaction transaction : Database.transactions) {
            FileManagement.writeToFile("transactions.csv", "transactions", transaction);
        }
        for (User user : Database.users) {
            FileManagement.writeToFile("users.csv", "users", user);
        }
        for (Product product : Database.products) {
            FileManagement.writeToFile("products.csv", "products", product);
        }
        for (Address address : Database.addresses) {
            FileManagement.writeToFile("addresses.csv", "addresses", address);
        }
    }
}
