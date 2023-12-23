package main.model.request;

import java.util.UUID;

public class AddressRequestDTO {
    private UUID addressID;
    private UUID userID;
    private String addressName;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public AddressRequestDTO(UUID addressID, UUID userID, String addressName, String street, String city, String state, String zipCode, String country) {
        this.addressID = addressID == null ? UUID.randomUUID() : addressID;
        this.userID = userID;
        this.addressName = addressName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public UUID getAddressID() {
        return addressID;
    }

    public void setAddressID(UUID addressID) {
        if (addressID != null) {
            this.addressID = addressID;
        }
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
