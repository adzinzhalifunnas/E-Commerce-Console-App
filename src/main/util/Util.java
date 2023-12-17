package main.util;

import java.util.Scanner;

public class Util {
    private static final Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public static int scanInt() {
        String input = scanner.nextLine();
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[Error] Please enter a valid integer.");
            return scanInt();
        }
        return Integer.parseInt(input);
    }

    public static String scanString() {
        String input = scanner.nextLine();
        if (input.equals("")) {
            System.out.println("[Error] Please enter a valid string.");
            return scanString();
        }
        return input;
    }

    public static String scanEmail() {
        String input = scanner.nextLine();
        if (!input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("[Error] Please enter a valid email.");
            return scanEmail();
        }
        return input;
    }

    public static String scanPassword() {
        String input = scanner.nextLine();
        if (input.length() < 5) {
            System.out.println("[Error] Please enter a password with at least 5 characters.");
            return scanPassword();
        }
        return input;
    }

    public static String scanName() {
        String input = scanner.nextLine();
        if (input.length() < 2) {
            System.out.println("[Error] Please enter a name with at least 2 characters.");
            return scanName();
        }
        return input;
    }

    public static String scanPhoneNumber() {
        String input = scanner.nextLine();
        if (!input.matches("^\\d{10,13}$")) {
            System.out.println("[Error] Please enter a valid phone number.");
            return scanPhoneNumber();
        }
        return input;
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
