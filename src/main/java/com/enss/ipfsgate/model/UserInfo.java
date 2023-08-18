package com.enss.ipfsgate.model;

public class UserInfo {

    private Integer id;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userEmail;
    private String priKey;
    private String pubKey;
    private String chainAddress;
    private Integer userType;   //用户类型：0普通用户，1审核权限，暂时仅由人工录入

    public UserInfo() {
    }

    public UserInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserInfo(String userName, String userPassword, String userPhone, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public UserInfo(Integer id, String userName, String userPassword, String userPhone, String userEmail, String priKey, String pubKey, String chainAddress, Integer userType) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.priKey = priKey;
        this.pubKey = pubKey;
        this.chainAddress = chainAddress;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getChainAddress() {
        return chainAddress;
    }

    public void setChainAddress(String chainAddress) {
        this.chainAddress = chainAddress;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", priKey='" + priKey + '\'' +
                ", pubKey='" + pubKey + '\'' +
                ", chainAddress='" + chainAddress + '\'' +
                ", userType=" + userType +
                '}';
    }
}