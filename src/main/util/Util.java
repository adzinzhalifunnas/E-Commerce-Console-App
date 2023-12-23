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

    public static Double scanDouble() {
        String input = scanner.nextLine();
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("[Error] Please enter a valid double.");
            return scanDouble();
        }
        return Double.parseDouble(input);
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

    public static String formatIDR(Double amount) {
        if (amount == 0) {
            return "IDR 0";
        }
        return String.format("IDR %,.2f", amount);
    }

    public static String getCurrentDateTime() {
        return java.time.LocalDateTime.now().toString();
    }

    public static String convertDateTime(String dateTime) {
        String[] dateTimeArr = dateTime.split("T");
        String[] dateArr = dateTimeArr[0].split("-");
        String[] timeArr = dateTimeArr[1].split(":");
        String month = "";
        switch (dateArr[1]) {
            case "01":
                month = "January";
                break;
            case "02":
                month = "February";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
        }
        String year = dateArr[0];
        String hour = timeArr[0];
        String minute = timeArr[1];
        String second = timeArr[2].substring(0, 2);
        return String.format("%s %s %s, %s:%s:%s", dateArr[2], month, year, hour, minute, second);
    }
}
