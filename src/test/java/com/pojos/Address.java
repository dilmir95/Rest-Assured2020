package com.pojos;

public class Address {

    private int addressId;
    private String street;
    private String city;
    private String state;
    private int zipCode;

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
