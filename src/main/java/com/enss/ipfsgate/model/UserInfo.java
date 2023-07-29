package com.enss.ipfsgate.model;

import java.util.Date;

public class UserInfo {
    /**
     * 主键唯一标识
     */
    private Integer uId;

    /**
     * 用户名
     */
    private String uName;

    /**
     * 密码
     */
    private String uPassword;

    /**
     * 状态
     */
    private Integer uState;

    /**
     * 类型
     */
    private Integer uType;

    /**
     * 手机号码
     */
    private String uPhone;

    /**
     * 创建时间
     */
    private Date uCreateTime;

    /**
     * 最后一次登录时间
     */
    private Date uLastLogin;

    /**
     * 智能合约账户
     */
    private String uContractAddress;


    /**
     * form账户地址
     */
    private String uFromAddress;

    /**
     * form账户密码
     */
    private String uFromPasswd;

    /**
     *
     * @mbg.generated 2020-02-14
     */
    public UserInfo(Integer uId, String uName, String uPassword, Integer uState, Integer uType, String uPhone,
                    Date uCreateTime, Date uLastLogin, String uContractAddress, String uFromAddress, String uFromPasswd) {
        this.uId = uId;
        this.uName = uName;
        this.uPassword = uPassword;
        this.uState = uState;
        this.uType = uType;
        this.uPhone = uPhone;
        this.uCreateTime = uCreateTime;
        this.uLastLogin = uLastLogin;
        this.uContractAddress = uContractAddress;
        this.uFromAddress = uFromAddress;
        this.uFromPasswd = uFromPasswd;
    }

    public UserInfo(String uName, String uPassword) {
        this.uName = uName;
        this.uPassword = uPassword;
    }

    public UserInfo(String uName, String uPassword, String uPhone) {
        this.uName = uName;
        this.uPhone = uPhone;
        this.uPassword = uPassword;
    }

    /**
     *
     * @mbg.generated 2020-02-14
     */
    public UserInfo() {
        super();
    }

    /**
     * 主键唯一标识
     * @return u_id 主键唯一标识
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 主键唯一标识
     * @param uId 主键唯一标识
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * 用户名
     * @return u_name 用户名
     */
    public String getuName() {
        return uName;
    }

    /**
     * 用户名
     * @param uName 用户名
     */
    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    /**
     * 密码
     * @return u_password 密码
     */
    public String getuPassword() {
        return uPassword;
    }

    /**
     * 密码
     * @param uPassword 密码
     */
    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    /**
     * 状态
     * @return u_state 状态
     */
    public Integer getuState() {
        return uState;
    }

    /**
     * 状态
     * @param uState 状态
     */
    public void setuState(Integer uState) {
        this.uState = uState;
    }

    /**
     * 类型
     * @return u_type 类型
     */
    public Integer getuType() {
        return uType;
    }

    /**
     * 类型
     * @param uType 类型
     */
    public void setuType(Integer uType) {
        this.uType = uType;
    }

    /**
     * 手机号码
     * @return u_phone 手机号码
     */
    public String getuPhone() {
        return uPhone;
    }

    /**
     * 手机号码
     * @param uPhone 手机号码
     */
    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    /**
     * 创建时间
     * @return u_create_time 创建时间
     */
    public Date getuCreateTime() {
        return uCreateTime;
    }

    /**
     * 创建时间
     * @param uCreateTime 创建时间
     */
    public void setuCreateTime(Date uCreateTime) {
        this.uCreateTime = uCreateTime;
    }

    /**
     * 最后一次登录时间
     * @return u_last_login 最后一次登录时间
     */
    public Date getuLastLogin() {
        return uLastLogin;
    }

    /**
     * 最后一次登录时间
     * @param uLastLogin 最后一次登录时间
     */
    public void setuLastLogin(Date uLastLogin) {
        this.uLastLogin = uLastLogin;
    }


    public String getuContractAddress() {
        return uContractAddress;
    }

    public void setuContractAddress(String uContractAddress) {
        this.uContractAddress = uContractAddress;
    }

    public String getuFromAddress() {
        return uFromAddress;
    }

    public void setuFromAddress(String uFromAddress) {
        this.uFromAddress = uFromAddress;
    }

    public String getuFromPasswd() {
        return uFromPasswd;
    }

    public void setuFromPasswd(String uFromPasswd) {
        this.uFromPasswd = uFromPasswd;
    }
}