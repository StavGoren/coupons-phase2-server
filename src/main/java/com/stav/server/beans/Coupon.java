package com.stav.server.beans;

import java.sql.Date;

public class Coupon {
    private int id;
    private String name;
    private double priceInNis;
    private String description;
    private Date startDate;
    private Date endDate;
    private int categoryId;
    private int companyId;
    private String couponNumber;


    public Coupon(){
    }

    public Coupon(int id, String name, double priceInNis, String description, Date startDate, Date endDate, int categoryId, int companyId, String couponNumber) {
        this(name, priceInNis, description, startDate, endDate, categoryId, companyId, couponNumber);
        this.id = id;
    }

    public Coupon(String name, double priceInNis, String description, Date startDate, Date endDate, int categoryId, int companyId, String couponNumber) {
        this.name = name;
        this.priceInNis = priceInNis;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categoryId = categoryId;
        this.companyId = companyId;
        this.couponNumber = couponNumber;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public String getCouponNumber(){
        return couponNumber;
    }
    public void setCouponNumber(String couponNumber){
        this.couponNumber = couponNumber;
    }

//    public void changeDateFormat(Date date) throws ParseException {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        date = simpleDateFormat.parse(String.valueOf(simpleDateFormat));
//    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + priceInNis +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", category='" + categoryId + '\'' +
                ", companyId=" + companyId +
                ", couponNumber='" + couponNumber + '\'' +
                '}' + '\n';
    }
}
