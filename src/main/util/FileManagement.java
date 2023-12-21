package main.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import main.helper.AddressHelper;
import main.helper.UserHelper;
import main.model.Address;
import main.model.User;
import main.model.request.AddressRequestDTO;
import main.model.request.UserRequestDTO;


public class FileManagement {
    public static <E> void readFromFile(String filePath, String type) {
        String pwd = System.getProperty("user.dir");
        String fullPath = String.format("%s/%s", pwd, filePath);
        Boolean isFileExist = false;
        try {
            isFileExist = new File(fullPath).exists();
        } catch (Exception e) {
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
            System.out.println("File not found!");
        }
    }

    public static <E> void writeToFile(String filePath, String type, E data) {
        String pwd = System.getProperty("user.dir");
        String fullPath = String.format("%s/%s", pwd, filePath);
        Boolean isFileExist = new File(fullPath).exists();
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
                        if (dataFromFileArray[2].equals(newData.getAddressName())) {
                            isDataExist = true;
                            break;
                        }
                    }
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
            System.out.println("File not found!");
        }
    }
}
