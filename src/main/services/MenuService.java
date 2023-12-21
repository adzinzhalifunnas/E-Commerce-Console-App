package main.services;

import main.database.Database;
import main.helper.UserHelper;
import main.model.User;
import main.util.*;

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
                System.out.println("Thank you for using " + Strings.appName);
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
        System.out.println("2. View Carts");
        System.out.println("3. View Orders");
        System.out.println("4. Settings");
        System.out.println("5. Logout");
        if (isError == 1) {
            System.out.printf("[Error] %d is not a valid choice.\nPlease try again!\n", errorChoice);
            isError = 0;
        }
        System.out.print(">> ");
        int choice = Util.scanInt();
        switch (choice) {
            case 1:
                System.out.println("View Products");
                break;
            case 2:
                System.out.println("View Carts");
                break;
            case 3:
                System.out.println("View Orders");
                break;
            case 4:
                showUserSettingsMenu(1);
                break;
            case 5:
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

    public static void showUserSettingsMenu(int roleID) {
        Util.clearScreen();
        System.out.printf(Strings.appName + " - %s Settings\n", (roleID == 1 ? "Customer" : "Seller"));
        UserHelper.viewUserSettings();
        Util.pressEnterToContinue();
        if (roleID == 1) {
            showCustomerMenu();
        } else {
            showSellerMenu();
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
                System.out.println("Manage Products");
                break;
            case 2:
                System.out.println("Manage Orders");
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
