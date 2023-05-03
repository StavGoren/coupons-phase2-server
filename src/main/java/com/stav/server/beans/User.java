package com.stav.server.beans;

import com.stav.server.enums.UserType;

public class User {
    private int id;
    private String userName;
    private String password;
    private UserType userType;
    private Integer companyId;



    public User(){

    }
    public User(int id, String userName, String password, UserType userType, Integer companyId) {
        this(userName, password, userType, companyId);
        this.id = id;
    }

    public User(String userName, String password, UserType userType, Integer companyId) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enum<UserType> getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + userName + '\'' +
                ", user_type=" + userType +
                ", company_id=" + companyId +
                ", password='" + password + '\'' +
                '}' + '\n';
    }
}