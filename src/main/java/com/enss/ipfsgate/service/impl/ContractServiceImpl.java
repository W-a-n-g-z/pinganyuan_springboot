package com.enss.ipfsgate.service.impl;


import com.enss.ipfsgate.mapper.ContractMapper;
import com.enss.ipfsgate.model.config.Contract;
import com.enss.ipfsgate.service.ContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;


    @Override
    public Integer insertContract(Contract contract) {
        return contractMapper.insertContract(contract);
    }

    @Override
    public String selectContractAddress(Integer contractTypeId) {
        return contractMapper.selectContractAddress(contractTypeId);
    }
}
