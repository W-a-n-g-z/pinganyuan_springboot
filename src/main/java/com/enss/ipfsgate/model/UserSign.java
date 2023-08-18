package com.enss.ipfsgate.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class UserSign {

    /*
    主键，自增
     */
    private Integer id;

    /*
    用户id，user表关联
     */
    private Integer userId;

    /*
    用户公钥
     */
    private String pubKey;

    /*
    用户私钥
     */
    private String priKey;

    /**
     * 账户地址
     */
    private String address;

    /*
    加密类型：1国密，0 ECDSA
     */
    private Integer encryptType;

    /*
    状态：1可用，0不可用
     */
    private Integer signState;

    /*
    创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /*
    更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date modifyTime;

    public UserSign() {
    }

    public UserSign(Integer id, Integer userId, String pubKey, String priKey, String address, Integer encryptType, Integer signState, Date createTime, Date modifyTime) {
        this.id = id;
        this.userId = userId;
        this.pubKey = pubKey;
        this.priKey = priKey;
        this.address = address;
        this.encryptType = encryptType;
        this.signState = signState;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(Integer encryptType) {
        this.encryptType = encryptType;
    }

    public Integer getSignState() {
        return signState;
    }

    public void setSignState(Integer signState) {
        this.signState = signState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
