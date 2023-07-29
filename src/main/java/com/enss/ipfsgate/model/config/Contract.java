package com.enss.ipfsgate.model.config;

public class Contract {

    private Integer id;

    private Integer contractType;

    private String contractName;

    private String contractAddress;

    public Contract() {
    }
    public Contract(Integer id, Integer contractType, String contractName, String contractAddress) {
        this.id = id;
        this.contractType = contractType;
        this.contractName = contractName;
        this.contractAddress = contractAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
