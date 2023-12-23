package main.services;

import java.util.UUID;

import main.database.Database;
import main.helper.ProductHelper;
import main.helper.TransactionHelper;
import main.helper.UserHelper;
import main.model.Address;
import main.model.Product;
import main.model.User;
import main.services.thread.ProcessTransactionThread;
import main.util.Util;

public class TransactionService {
    public static void addTransaction(UUID productID) {
        UUID buyerID = Database.loggedInUser.getUserID();
        Product product = ProductHelper.getProduct(productID);
        User buyer = UserHelper.getUserDetail(buyerID);
        if (product != null) {
            System.out.printf("Enter quantity [1-%d]: ", product.getProductStock());
            int quantity = Util.scanInt();
            if (quantity >= 1 && quantity <= product.getProductStock()) {
                double totalPrice = quantity * product.getProductPrice();
                double userBalance = buyer.getBalance();
                if (userBalance >= totalPrice) {
                    System.out.printf("Total price: %s\n", Util.formatIDR(totalPrice));
                    System.out.print("Confirm transaction [Y/N]: ");
                    String confirm = Util.scanString();
                    if (confirm.equalsIgnoreCase("Y")) {
                        AddressService.viewAddress(buyerID);
                        System.out.print("Enter address name: ");
                        String addressName = Util.scanString();
                        Address chosenAddress = AddressService.getAddressDetail(buyerID, addressName);
                        if (chosenAddress != null) {
                            ProcessTransactionThread processTransactionThread = new ProcessTransactionThread(productID, chosenAddress.getAddressID(), quantity, totalPrice);
                            Thread thread = new Thread(processTransactionThread);
                            thread.start();
                            System.out.println("[Info] Transaction has been processed.");
                        } else {
                            System.out.println("[Error] Address not found.");
                            Util.pressEnterToContinue();
                            addTransaction(productID);
                        }
                    } else if (confirm.equalsIgnoreCase("N")) {
                        System.out.println("[Error] Transaction has been cancelled.");
                        Util.pressEnterToContinue();
                        MenuService.customerViewProductsMenu();
                    } else {
                        System.out.println("[Error] Invalid input.");
                        Util.pressEnterToContinue();
                        addTransaction(productID);
                    }
                } else {
                    System.out.println("[Error] Insufficient balance.");
                    Util.pressEnterToContinue();
                    MenuService.customerViewProductsMenu();
                }
            } else {
                System.out.println("[Error] Invalid quantity.");
                Util.pressEnterToContinue();
                addTransaction(productID);
            }
        }
    }

    public static void viewBuyerTransactions() {
        UUID buyerID = Database.loggedInUser.getUserID();
        TransactionHelper.viewBuyerTransactions(buyerID);
    }

    public static void viewSellerTransactions() {
        UUID sellerID = Database.loggedInUser.getUserID();
        TransactionHelper.viewSellerTransactions(sellerID);
    }
}