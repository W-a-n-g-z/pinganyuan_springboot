package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.config.Contract;
import org.springframework.stereotype.Service;

@Service
public interface ContractService {

    public Integer insertContract(Contract contract);

    public String selectContractAddress(Integer contractTypeId);

}
