package com.stav.server.beans;

public class Customer {

//    private int id;
    private User user;
    private String fullName;
    private String phoneNumber;
    private String address;


    public Customer(){

    }
    public Customer(User user, String fullName, String phoneNumber, String address) {
        this.user = user;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public void setId(int id){
        this.user.setId(id);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "userId=" + user.getId() +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}' + '\n';
    }
}
