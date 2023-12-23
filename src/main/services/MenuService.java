package main.services;

import java.util.UUID;

import main.database.Database;
import main.helper.UserHelper;
import main.model.User;
import main.util.BackupData;
import main.util.Strings;
import main.util.Util;

public class MenuService {
    private static int isError = 0;
    private static int errorChoice = 0;

    public static void showMainMenu() {
        Util.clearScreen();
        System.out.println("Welcome to " + Strings.appName);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        if (isError == 1) {
            System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", errorChoice);
            isError = 0;
        }
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                showRegisterMenu();
                break;
            case 2:
                showLoginMenu();
                break;
            case 3:
                BackupData.backupData();
                System.out.printf("Thank you for using %s. See you again!\n", Strings.appName);
                break;
            case 99:
                secretMessage();
                Util.pressEnterToContinue();
                secretMenu();
            default:
                isError = 1;
                errorChoice = choice;
                showMainMenu();
                break;
        }
    }

    public static void showCustomerMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Customer Menu");
        System.out.println("1. View Products");
        System.out.println("2. View Orders");
        System.out.println("3. Settings");
        System.out.println("4. Logout");
        if (isError == 1) {
            System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", errorChoice);
            isError = 0;
        }
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                customerViewProductsMenu();
                break;
            case 2:
                customerViewOrdersMenu();
                break;
            case 3:
                showUserSettingsMenu(1);
                break;
            case 4:
                UserHelper.logoutUser();
                System.out.println("Logout");
                showMainMenu();
                break;
            default:
                isError = 1;
                errorChoice = choice;
                showCustomerMenu();
                break;
        }
    }

    public static void customerViewProductsMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - View Products");
        ProductService.viewAllProducts();
        System.out.println("\nChoose number of product you want to buy or choose 0 to go back to Customer Menu:");
        System.out.print(">> ");
        int choice = Util.scanInt();
        if (choice == 0) {
            showCustomerMenu();
        } else {
            int totalProduct = Database.products.size();
            if (choice > totalProduct) {
                System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", choice);
                Util.pressEnterToContinue();
                customerViewProductsMenu();
            } else {
                Boolean isProductOutOfStock = ProductService.isProductOutOfStock(Database.products.get(choice - 1).getProductID());
                if (isProductOutOfStock) {
                    System.out.println("[Error] Product is out of stock.");
                    Util.pressEnterToContinue();
                    customerViewProductsMenu();
                } else {
                    UUID chosenProductID = Database.products.get(choice - 1).getProductID();
                    TransactionService.addTransaction(chosenProductID);
                    System.out.println("[Info] Transaction has been completed.");
                    Util.pressEnterToContinue();
                    customerViewProductsMenu();
                }
            }
        }
    }

    public static void customerViewOrdersMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - View Orders");
        TransactionService.viewBuyerTransactions();
        System.out.println("\nChoose one of the following options:");
        System.out.println("1. Back to Customer Menu");
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                showCustomerMenu();
                break;
            default:
                isError = 1;
                errorChoice = choice;
                customerViewOrdersMenu();
                break;
        }
    }

    public static void showUserSettingsMenu(int roleID) {
        Util.clearScreen();
        String object = roleID == 1 ? "Customer" : "Seller";
        System.out.printf(Strings.appName + " - %s Settings\n", object);
        UserHelper.viewUserSettings();
        System.out.println("\nChoose one of the following options:");
        System.out.println("1. Add Address");
        System.out.printf("2. Back to %s Menu\n", object);
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                if (roleID == 1) {
                    AddressService.addAddress(Database.loggedInUser.getUserID());
                    System.out.println("Successfully added address!");
                    Util.pressEnterToContinue();
                    showUserSettingsMenu(roleID);
                } else {
                    System.out.println("You are not allowed to add address!");
                    System.out.println("This feature is only available for customer.");
                    Util.pressEnterToContinue();
                    showUserSettingsMenu(roleID);
                }
            case 2:
                if (roleID == 1) {
                    showCustomerMenu();
                } else {
                    showSellerMenu();
                }
                break;
            default:
                isError = 1;
                errorChoice = choice;
                showUserSettingsMenu(roleID);
                break;
        }
    }

    public static void showSellerMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Seller Menu");
        System.out.println("1. Manage Products");
        System.out.println("2. Manage Orders");
        System.out.println("3. Settings");
        System.out.println("4. Logout");
        if (isError == 1) {
            System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", errorChoice);
            isError = 0;
        }
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                sellerManageProductsMenu();
                break;
            case 2:
                sellerManageOrdersMenu();
                break;
            case 3:
                showUserSettingsMenu(2);
                break;
            case 4:
                UserHelper.logoutUser();
                System.out.println("Logout");
                showMainMenu();
                break;
            default:
                isError = 1;
                errorChoice = choice;
                showSellerMenu();
                break;
        }
    }

    public static void sellerManageProductsMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Manage Products");
        ProductService.viewUserProducts();
        System.out.println("\nChoose one of the following options:");
        System.out.println("1. Add Product");
        System.out.println("2. Back to Seller Menu");
        if (isError == 1) {
            System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", errorChoice);
            isError = 0;
        }
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                ProductService.addProduct();
                System.out.println("Successfully added product!");
                Util.pressEnterToContinue();
                sellerManageProductsMenu();
                break;
            case 2:
                showSellerMenu();
                break;
            default:
                isError = 1;
                errorChoice = choice;
                sellerManageProductsMenu();
                break;
        }
    }

    public static void sellerManageOrdersMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Manage Orders");
        TransactionService.viewSellerTransactions();
        System.out.println("\nChoose one of the following options:");
        System.out.println("1. Back to Seller Menu");
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                showSellerMenu();
                break;
            default:
                isError = 1;
                errorChoice = choice;
                sellerManageOrdersMenu();
                break;
        }
    }

    public static void showRegisterMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Register");
        System.out.println("1. Register as Customer");
        System.out.println("2. Register as Seller");
        System.out.println("3. Back to Main Menu");
        if (isError == 1) {
            System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", errorChoice);
            isError = 0;
        }
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                registerUser(1);
                break;
            case 2:
                registerUser(2);
                break;
            case 3:
                showMainMenu();
                break;
            default:
                isError = 1;
                errorChoice = choice;
                showRegisterMenu();
                break;
        }
    }

    public static void registerUser(int roleID) {
        Util.clearScreen();
        String object = roleID == 1 ? "Customer" : "Seller";
        System.out.println(Strings.appName + " - Register as " + object);
        System.out.println("Are you sure you want to register as " + object + "? [Y/N] (case insensitive)");
        System.out.print(">> ");
        String choice = Util.scanString();
        if (choice.equalsIgnoreCase("y")) {
            UserService.addUser(roleID);
            System.out.println("You have successfully registered as " + object + "!");
            Util.pressEnterToContinue();
            showMainMenu();
        } else if (choice.equalsIgnoreCase("n")) {
            showRegisterMenu();
        } else {
            registerUser(roleID);
        }
    }

    public static void showLoginMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Login");
        System.out.println("1. Login as Customer");
        System.out.println("2. Login as Seller");
        System.out.println("3. Back to Main Menu");
        if (isError == 1) {
            System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", errorChoice);
            isError = 0;
        }
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                loginUser(1);
                break;
            case 2:
                loginUser(2);
                break;
            case 3:
                showMainMenu();
                break;
            default:
                isError = 1;
                errorChoice = choice;
                showLoginMenu();
                break;
        }
    }

    public static void loginUser(int roleID) {
        Util.clearScreen();
        String object = roleID == 1 ? "Customer" : "Seller";
        System.out.println(Strings.appName + " - Login as " + object);
        System.out.println("Are you sure you want to login as " + object + "? [Y/N] (case insensitive)");
        System.out.print(">> ");
        String choice = Util.scanString();
        if (choice.equalsIgnoreCase("y")) {
            Boolean isLogin = UserService.loginUser(roleID);
            if (isLogin) {
                System.out.println("You have successfully logged in as " + object + "!");
                User user = Database.loggedInUser;
                Boolean isAddressExist = UserHelper.checkUserAddress(user.getUserID());
                if (!isAddressExist) {
                    System.out.println("You have not set your address yet.");
                    System.out.println("Please set your address first.");
                    Util.pressEnterToContinue();
                    AddressService.addAddress(user.getUserID());
                }
                Util.pressEnterToContinue();
                if (roleID == 1) {
                    showCustomerMenu();
                } else {
                    showSellerMenu();
                }
            } else {
                System.out.println("[Error] Invalid email or password.");
                Util.pressEnterToContinue();
                loginUser(roleID);
            }
        } else if (choice.equalsIgnoreCase("n")) {
            showLoginMenu();
        } else {
            loginUser(roleID);
        }
    }

    private static void secretMessage() {
        Util.clearScreen();
        System.out.println("This is a secret menu!");
        System.out.println("Psst, please don't tell anyone!");
        System.out.println("Because this is a secret menu, you can do anything here!");
        System.out.println("My Lord, Adzin. Waiting for your presence ^_^");
    }

    private static void secretMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Secret Menu");
        System.out.println("1. View All Customers");
        System.out.println("2. View All Sellers");
        System.out.println("3. Back to Main Menu");
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                UserHelper.viewAllUsers(1);
                Util.pressEnterToContinue();
                secretMenu();
                break;
            case 2:
                UserHelper.viewAllUsers(2);
                Util.pressEnterToContinue();
                secretMenu();
                break;
            case 3:
                showMainMenu();
                break;
            default:
                secretMenu();
                break;
        }
    }
}
