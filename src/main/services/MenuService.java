package main.services;

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
                System.out.println("Register as Customer");
                break;
            case 2:
                System.out.println("Register as Seller");
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
                System.out.println("Login as Customer");
                break;
            case 2:
                System.out.println("Login as Seller");
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
}
