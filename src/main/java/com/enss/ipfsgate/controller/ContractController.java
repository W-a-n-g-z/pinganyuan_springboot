//发布合约
package com.enss.ipfsgate.controller;

import com.alibaba.fastjson.JSON;
import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.model.config.Contract;
import com.enss.ipfsgate.model.threat.FullLogInfo;
import com.enss.ipfsgate.service.ContractService;
import com.enss.ipfsgate.service.FullLogInfoService;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.fisco.web3j.FiscoUtil;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/contract")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @Autowired
    private FiscoUtil fiscoUtil;

    /**
     * 发布智能合约，1验证用户零知识
     */
    @RequestMapping(value = "deployContractBase")
    public Resp deployFullLogBase(@RequestParam Integer contractType,@RequestParam String contractName){
        TransactionResponse deployResult= null;
        System.out.println("contractType:"+contractType);
        System.out.println("contractName:"+contractName);
        if(1==contractType){
            deployResult = fiscoUtil.deployFiatShamirBase();
        }else{
            return Resp.error("不支持的合约类型");
        }
        if(null==deployResult){
            return Resp.error("合约发布失败");
        }

        String contractAddress = deployResult.getContractAddress();
        Contract contract = new Contract();
        contract.setContractType(contractType);
        contract.setContractName(contractName);
        contract.setContractAddress(contractAddress);
        int addResult = contractService.insertContract(contract);
        if(addResult==1){
            return Resp.success("合约地址："+contractAddress);
        }else{
            return Resp.error("合约地址入库失败！");
        }
    }

}
