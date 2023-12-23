package main.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import main.helper.AddressHelper;
import main.helper.ProductHelper;
import main.helper.TransactionHelper;
import main.helper.UserHelper;
import main.model.Address;
import main.model.Product;
import main.model.Transaction;
import main.model.User;
import main.model.request.AddressRequestDTO;
import main.model.request.ProductRequestDTO;
import main.model.request.TransactionRequestDTO;
import main.model.request.UserRequestDTO;

public class FileManagement {
    public static <E> void readFromFile(String filePath, String type) {
        String pwd = System.getProperty("user.dir");
        String fullPath = String.format("%s/%s", pwd, filePath);
        Boolean isFileExist = false;
        isFileExist = new File(fullPath).exists();
        if (!isFileExist) {
            fullPath = String.format("%s/src/%s", pwd, filePath);
            isFileExist = new File(fullPath).exists();
        }
        if (isFileExist) {
            try {
                File file = new File(fullPath);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    String[] dataArray = data.split(",");
                    if (type.equals("users")) {
                        UserRequestDTO userRequestDTO = new UserRequestDTO(
                            UUID.fromString(dataArray[0]),
                            dataArray[1],
                            dataArray[2],
                            dataArray[3],
                            dataArray[4],
                            dataArray[5],
                            Double.parseDouble(dataArray[6]),
                            Integer.parseInt(dataArray[7])
                        );
                        UserHelper.addUser(userRequestDTO);
                    } else if (type.equals("addresses")) {
                        AddressRequestDTO addressRequestDTO = new AddressRequestDTO(
                            UUID.fromString(dataArray[0]),
                            UUID.fromString(dataArray[1]),
                            dataArray[2],
                            dataArray[3],
                            dataArray[4],
                            dataArray[5],
                            dataArray[6],
                            dataArray[7]
                        );
                        AddressHelper.addAddress(addressRequestDTO);
                    } else if (type.equals("products")) {
                        ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                            UUID.fromString(dataArray[0]),
                            UUID.fromString(dataArray[1]),
                            dataArray[2],
                            dataArray[3],
                            Double.parseDouble(dataArray[4]),
                            Integer.parseInt(dataArray[5]),
                            Integer.parseInt(dataArray[6])
                        );
                        ProductHelper.addProduct(productRequestDTO);
                    } else if (type.equals("transactions")) {
                        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO(
                            UUID.fromString(dataArray[0]),
                            UUID.fromString(dataArray[1]),
                            UUID.fromString(dataArray[2]),
                            UUID.fromString(dataArray[3]),
                            UUID.fromString(dataArray[4]),
                            Integer.parseInt(dataArray[5]),
                            Double.parseDouble(dataArray[6]),
                            dataArray[7],
                            dataArray[8]
                        );
                        TransactionHelper.addTransaction(transactionRequestDTO);
                    } else {
                        System.out.println("Type not found!");
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.printf("File %s not found!\n", filePath);
        }
    }

    public static <E> void writeToFile(String filePath, String type, E data) {
        String pwd = System.getProperty("user.dir");
        String fullPath = String.format("%s/%s", pwd, filePath);
        Boolean isFileExist = false;
        isFileExist = new File(fullPath).exists();
        if (!isFileExist) {
            fullPath = String.format("%s/src/%s", pwd, filePath);
            isFileExist = new File(fullPath).exists();
        }
        if (isFileExist) {
            try {
                Boolean isDataExist = false;
                File file = new File(fullPath);
                Scanner scanner = new Scanner(file);
                if (type.equals("users")) {
                    User newData = (User) data;
                    while (scanner.hasNextLine()) {
                        String dataFromFile = scanner.nextLine();
                        String[] dataFromFileArray = dataFromFile.split(",");
                        if (dataFromFileArray[1].equals(newData.getEmail())) {
                            isDataExist = true;
                            break;
                        }
                    }
                } else if (type.equals("addresses")) {
                    Address newData = (Address) data;
                    while (scanner.hasNextLine()) {
                        String dataFromFile = scanner.nextLine();
                        String[] dataFromFileArray = dataFromFile.split(",");
                        if (dataFromFileArray[1].equals(newData.getUserID().toString()) && dataFromFileArray[2].equals(newData.getAddressName())) {
                            isDataExist = true;
                            break;
                        }
                    }
                } else if (type.equals("products")) {
                    Product newData = (Product) data;
                    while (scanner.hasNextLine()) {
                        String dataFromFile = scanner.nextLine();
                        String[] dataFromFileArray = dataFromFile.split(",");
                        if (dataFromFileArray[1].equals(newData.getProductSellerID().toString()) && dataFromFileArray[2].equals(newData.getProductName())) {
                            isDataExist = true;
                            break;
                        }
                    }
                } else if (type.equals("transactions")) {
                    Transaction newData = (Transaction) data;
                    while (scanner.hasNextLine()) {
                        String dataFromFile = scanner.nextLine();
                        String[] dataFromFileArray = dataFromFile.split(",");
                        if (dataFromFileArray[0].equals(newData.getTransactionID().toString())) {
                            isDataExist = true;
                            break;
                        }
                    }
                } else {
                    System.out.println("Type not found!");
                }
                scanner.close();
                if (!isDataExist) {
                    try {
                        FileWriter fileWriter = new FileWriter(file, true);
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        if (type.equals("users")) {
                            User newData = (User) data;
                            printWriter.printf("%s,%s,%s,%s,%s,%s,%f,%d\n", newData.getUserID(), newData.getEmail(), newData.getPassword(), newData.getFirstName(), newData.getLastName(), newData.getPhoneNumber(), newData.getBalance(), newData.getRoleID());
                        } else if (type.equals("addresses")) {
                            Address newData = (Address) data;
                            printWriter.printf("%s,%s,%s,%s,%s,%s,%s,%s\n", newData.getAddressID(), newData.getUserID(), newData.getAddressName(), newData.getStreet(), newData.getCity(), newData.getState(), newData.getZipCode(), newData.getCountry());
                        } else if (type.equals("products")) {
                            Product newData = (Product) data;
                            printWriter.printf("%s,%s,%s,%s,%f,%d,%d\n", newData.getProductID(), newData.getProductSellerID(), newData.getProductName(), newData.getProductDescription(), newData.getProductPrice(), newData.getProductStock(), newData.getProductSold());
                        } else if (type.equals("transactions")) {
                            Transaction newData = (Transaction) data;
                            printWriter.printf("%s,%s,%s,%s,%s,%d,%f,%s,%s\n", newData.getTransactionID(), newData.getCustomerID(), newData.getSellerID(), newData.getProductID(), newData.getAddressID(), newData.getQuantity(), newData.getTotalPrice(), newData.getTransactionDate(), newData.getTransactionStatus());
                        }
                        printWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.printf("File %s not found!\n", filePath);
        }
    }

    public static void clearFile(String filePath) {
        String pwd = System.getProperty("user.dir");
        String fullPath = String.format("%s/%s", pwd, filePath);
        Boolean isFileExist = false;
        isFileExist = new File(fullPath).exists();
        if (!isFileExist) {
            fullPath = String.format("%s/src/%s", pwd, filePath);
            isFileExist = new File(fullPath).exists();
        }
        if (isFileExist) {
            try {
                FileWriter fileWriter = new FileWriter(fullPath);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print("");
                printWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.printf("File %s not found!\n", filePath);
        }
    }
}
