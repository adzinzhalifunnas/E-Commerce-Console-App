package main.services;

import java.util.UUID;

import main.helper.AddressHelper;
import main.model.Address;
import main.model.request.AddressRequestDTO;
import main.util.Util;

public class AddressService {
    public static void addAddress(UUID userID) {
        String addressName, street, city, state, zipCode, country;
        do {
            System.out.print("Enter Address Name [3..50]: ");
            addressName = Util.scanString();
        } while (addressName.length() < 3 || addressName.length() > 50);
        do {
            System.out.print("Enter Street [3..50]: ");
            street = Util.scanString();
        } while (street.length() < 3 || street.length() > 50);
        do {
            System.out.print("Enter City [3..20]: ");
            city = Util.scanString();
        } while (city.length() < 3 || city.length() > 20);
        do {
            System.out.print("Enter State [3..20]: ");
            state = Util.scanString();
        } while (state.length() < 3 || state.length() > 20);
        do {
            System.out.print("Enter Zip Code [5..10]: ");
            zipCode = Util.scanString();
        } while (zipCode.length() < 5 || zipCode.length() > 10);
        do {
            System.out.print("Enter Country [3..20]: ");
            country = Util.scanString();
        } while (country.length() < 3 || country.length() > 20);
        AddressRequestDTO addressRequestDTO = new AddressRequestDTO(null, userID, addressName, street, city, state, zipCode, country);
        AddressHelper.addAddress(addressRequestDTO);
    }

    public static void viewAddress(UUID userID) {
        AddressHelper.viewUserAddresses(userID);
    }

    public static Address getAddressDetail(UUID userID, String addressName) {
        return AddressHelper.getAddressDetail(userID, addressName);
    }
}
