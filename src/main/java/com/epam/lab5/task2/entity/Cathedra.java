package com.epam.lab5.task2.entity;

public class Cathedra {
    private int cathedraId;
    private String cathedraName;
    private String cathedraEmail;
    private String cathedraPhone;
    private String cathedraDescription;
    private Address address;
    private int addressId;

    public Cathedra() {
    }

    public Cathedra(int cathedraId) {
        this.cathedraId = cathedraId;
    }

    public Cathedra(String cathedraName, String cathedraEmail, String cathedraPhone, String cathedraDescription, Address address) {
        this.cathedraName = cathedraName;
        this.cathedraEmail = cathedraEmail;
        this.cathedraPhone = cathedraPhone;
        this.cathedraDescription = cathedraDescription;
        this.address = address;
    }

    public Cathedra(int cathedraId, String cathedraName, String cathedraEmail, String cathedraPhone, String cathedraDescription, int addressId) {
        this.cathedraId = cathedraId;
        this.cathedraName = cathedraName;
        this.cathedraEmail = cathedraEmail;
        this.cathedraPhone = cathedraPhone;
        this.cathedraDescription = cathedraDescription;
        this.addressId = addressId;
    }

    public int getCathedraId() {
        return cathedraId;
    }

    public void setCathedraId(int cathedraId) {
        this.cathedraId = cathedraId;
    }

    public String getCathedraName() {
        return cathedraName;
    }

    public void setCathedraName(String cathedraName) {
        this.cathedraName = cathedraName;
    }

    public String getCathedraDescription() {
        return cathedraDescription;
    }

    public void setCathedraDescription(String cathedraDescription) {
        this.cathedraDescription = cathedraDescription;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCathedraEmail() {
        return cathedraEmail;
    }

    public void setCathedraEmail(String cathedraEmail) {
        this.cathedraEmail = cathedraEmail;
    }

    public String getCathedraPhone() {
        return cathedraPhone;
    }

    public void setCathedraPhone(String cathedraPhone) {
        this.cathedraPhone = cathedraPhone;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return  "\tname: " + cathedraName +
                "\n\te-mail: " + cathedraEmail +
                "\n\tphone: " + cathedraPhone +
                "\n\tdescription: " + cathedraDescription +
                "\n\tcathedra address: " + addressId;
    }
}