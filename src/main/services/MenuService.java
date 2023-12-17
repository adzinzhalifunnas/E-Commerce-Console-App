package main.services;

import main.helper.UserHelper;
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
        System.out.println("2. View Cart");
        System.out.println("3. View Orders");
        System.out.println("4. Logout");
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
                System.out.println("View Cart");
                break;
            case 3:
                System.out.println("View Orders");
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

    public static void showSellerMenu() {
        Util.clearScreen();
        System.out.println(Strings.appName + " - Seller Menu");
        System.out.println("1. View Products");
        System.out.println("2. View Orders");
        System.out.println("3. Logout");
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
                System.out.println("View Orders");
                break;
            case 3:
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
                Util.pressEnterToContinue();
                if (roleID == 1) {
                    showCustomerMenu();
                } else {
                    showSellerMenu();
                }
            } else {
                System.out.println("[Error] Invalid email or password.");
                Util.pressEnterToContinue();
                showLoginMenu();
            }
        } else if (choice.equalsIgnoreCase("n")) {
            showLoginMenu();
        } else {
            loginUser(roleID);
        }
    }
}
