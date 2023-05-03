package com.stav.server.dto;

import java.sql.Date;

public class PurchaseCustomerDto {
    private int id;
    private int amount;
    private Date date;
    private int userId;
    private String fullName;
    private String phoneNumber;
    private String address;


    public PurchaseCustomerDto(){

    }

    public PurchaseCustomerDto(int id, int amount, Date date, int userId, String fullName, String phoneNumber, String address) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "PurchaseCustomerDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", user=" + userId +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}' + "\n";
    }
}
