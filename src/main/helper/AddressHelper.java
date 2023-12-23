package main.helper;

import java.util.UUID;

import main.database.Database;
import main.model.Address;
import main.model.request.AddressRequestDTO;
import main.util.FileManagement;

public class AddressHelper {
    public static Boolean isAddressExist(UUID userID, String name) {
        for (Address address : Database.addresses) {
            if (address.getUserID().equals(userID) && address.getAddressName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void addAddress(AddressRequestDTO addressRequestDTO) {
        Boolean isAddressExist = isAddressExist(addressRequestDTO.getUserID(), addressRequestDTO.getAddressName());
        if (!isAddressExist) {
            Address address = new Address(
                addressRequestDTO.getAddressID() == null ? UUID.randomUUID() : addressRequestDTO.getAddressID(),
                addressRequestDTO.getUserID(),
                addressRequestDTO.getAddressName(),
                addressRequestDTO.getStreet(),
                addressRequestDTO.getCity(),
                addressRequestDTO.getState(),
                addressRequestDTO.getZipCode(),
                addressRequestDTO.getCountry()
            );
            Database.addresses.add(address);
            FileManagement.writeToFile("addresses.csv", "addresses", address);
        }
    }

    public static void viewUserAddresses(UUID userID) {
        int no = 0;
        for (Address address : Database.addresses) {
            if (address.getUserID().equals(userID)) {
                System.out.printf("   - Address ID: %s\n", address.getAddressID());
                System.out.printf("   - Address Name: %s\n", address.getAddressName());
                System.out.printf("   - Street: %s\n", address.getStreet());
                System.out.printf("   - City: %s\n", address.getCity());
                System.out.printf("   - State: %s\n", address.getState());
                System.out.printf("   - Zip Code: %s\n", address.getZipCode());
                System.out.printf("   - Country: %s\n", address.getCountry());
                System.out.println();
                no++;
            }
        }
        if (no == 0) {
            System.out.println("[Info] You have no address yet.");
        }
    }

    public static Address getAddressDetail(UUID userID, String addressName) {
        for (Address address : Database.addresses) {
            if (address.getUserID().equals(userID) && address.getAddressName().equals(addressName)) {
                return address;
            }
        }
        return null;
    }
}