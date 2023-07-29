package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.config.Contract;
import org.springframework.stereotype.Component;

@Component
public interface ContractMapper {

    public Integer insertContract(Contract contract);

    public String selectContractAddress(Integer contractTypeId);

}
