package com.epam.lab5.task2.entity;

public class Address {
    private int addressId;
    private String country;
    private String region;
    private String city;
    private String street;
    private String building;
    private String flat;

    //foreign keys
    private int cathedraId;

    public Address() {
    }

    public Address(int addressId) {
        this.addressId = addressId;
    }

    public Address(String country, String region, String city, String street, String building, String flat) {
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.building = building;
        this.flat = flat;
    }

    public Address(int addressId, String country, String region, String city, String street, String building, String flat) {
        this.addressId = addressId;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.building = building;
        this.flat = flat;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public int getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(int cathedraId) {
        this.cathedraId = cathedraId;
    }

    @Override
    public String toString() {
        return "\taddress id: " + addressId +
                "\n\tcountry: " + country +
                "\n\tregion: " + region +
                "\n\tcity: " + city +
                "\n\tstreet: " + street +
                "\n\tbuilding: " + building +
                "\n\tflat: " + flat;
    }
}