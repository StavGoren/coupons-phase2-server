package com.stav.server.beans;

import java.sql.Date;

public class Purchase {
    private int id;
    private int customerId;
    private int couponId;
    private int amount;
    private Date date;


    public Purchase(){

    }

    public Purchase(int id, int customerId, int couponId, int amount, Date date) {
        this(customerId, couponId, amount, date);
        this.id = id;
    }

    public Purchase(int customerId, int couponId, int amount, Date date) {
        this.customerId = customerId;
        this.couponId = couponId;
        this.amount = amount;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
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

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", couponId=" + couponId +
                ", amount=" + amount +
                ", date=" + date +
                '}' + "\n";
    }
}
