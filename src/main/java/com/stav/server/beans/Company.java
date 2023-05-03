package com.stav.server.beans;

public class Company {
    private int id;
    private String name;
    private String phoneNumber;
    private String address;

    public Company(){
    }

    public Company(int id, String name, String phoneNumber, String address) {
        this(name, phoneNumber, address);
        this.id = id;
    }
    public Company(String name, String phoneNumber, String address) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}' + '\n';
    }
}
