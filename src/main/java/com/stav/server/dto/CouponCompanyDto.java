package com.stav.server.dto;

import com.stav.server.beans.Company;
import com.stav.server.beans.Coupon;

import java.sql.Date;

public class CouponCompanyDto {
    private int id;
    private String couponName;
    private double priceInNis;
    private String description;
    private Date startDate;
    private Date endDate;
    private int companyId;
    private String companyName;
    private String phoneNumber;



    public CouponCompanyDto(){

    }


    public CouponCompanyDto(Coupon coupon, Company company) {
        this.id = coupon.getId();
        this.couponName = coupon.getName();
        this.priceInNis = coupon.getPriceInNis();
        this.description = coupon.getDescription();
        this.startDate = coupon.getStartDate();
        this.endDate = coupon.getEndDate();
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.phoneNumber = company.getPhoneNumber();
    }


    public CouponCompanyDto(int id, String couponName, double priceInNis, String description, Date startDate, Date endDate, int companyId, String companyName, String phoneNumber) {
        this.id = id;
        this.couponName = couponName;
        this.priceInNis = priceInNis;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.companyId = companyId;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public double getPriceInNis() {
        return priceInNis;
    }

    public void setPriceInNis(double priceInNis) {
        this.priceInNis = priceInNis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "CouponCompanyDto{" +
                "id=" + id +
                ", couponName='" + couponName + '\'' +
                ", priceInNis=" + priceInNis +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}' + "\n";
    }
}
