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
    private FullLogInfoService fullLogInfoService;

    @Autowired
    private FiscoUtil fiscoUtil;

    /**
     * 发布智能合约，key-value结构，1全量日志，2威胁处理信息，3模型
     */
    @RequestMapping(value = "deployContractBase")
    public Resp deployFullLogBase(@RequestParam Integer contractType,@RequestParam String contractName){
        TransactionResponse deployResult= null;
        System.out.println("contractType:"+contractType);
        System.out.println("contractName:"+contractName);
        if(1==contractType){
            deployResult = fiscoUtil.deployFullLogBase();
        }else if(2==contractType){
            deployResult = fiscoUtil.deployThreatResultBase();
        }else if(3==contractType){
            deployResult = fiscoUtil.deployPredictModelBase();
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

    /**
     * 发布全量日志数据上珊瑚链使用的智能合约，key-value结构
     */
    @RequestMapping(value = "deployFullLogBase")
    public Resp deployFullLogBase(){
        TransactionResponse deployResult = fiscoUtil.deployFullLogBase();
        String contractAddress = deployResult.getContractAddress();
        Contract contract = new Contract();
        contract.setContractType(1);
        contract.setContractName("珊瑚链全量日志数据上链合约 v0.0.1");
        contract.setContractAddress(contractAddress);
        int addResult = contractService.insertContract(contract);
        if(addResult==1){
            return Resp.success("合约地址："+contractAddress);
        }else{
            return Resp.error("合约地址入库失败！");
        }
    }

    /**
     * 测试全量日志数据上珊瑚链使用的智能合约
     */
    @RequestMapping(value = "testFullLogBase")
    public Resp testFullLogBase() throws Exception {
        String contractAddress = contractService.selectContractAddress(1);
        FullLogInfo fullLogInfo = new FullLogInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date logDate = null;
        try {
            logDate = sdf.parse("2022-10-09 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        fullLogInfo.setLogName("netlog-192.168.1.154-2022-10-9.log");
        fullLogInfo.setLogDate(logDate);
        fullLogInfo.setLogSize(635);
        fullLogInfo.setLogSource("192.168.1.154");
        fullLogInfo.setLogTypeName("netlog");
        fullLogInfo.setIpfsHash("QmQmnkDXKZMGytcjBtqQQCvzWYzvhyDKDYoDuh4PHJbQbg");
        fullLogInfo.setIpfsState(1);

        //上链
        TransactionResponse transactionResponse = fiscoUtil.upFullLogChainInfo(AppConfigSchedule.upChainKey, fullLogInfo.getIpfsHash(), JSON.toJSONString(fullLogInfo), contractAddress);
        TransactionReceipt transactionReceipt = transactionResponse.getTransactionReceipt();
        String blockHash = transactionReceipt.getBlockHash();
        String blockNumberStr = transactionReceipt.getBlockNumber().substring(2);
        int blockNumberInteger = Integer.parseInt(blockNumberStr, 16);
        BigInteger blockNumber = new BigInteger("" + blockNumberInteger);
        String txHash = transactionReceipt.getTransactionHash();
        System.out.println("上链成功，交易哈希为："+txHash+"，块高为"+blockNumber+"，块哈希为："+blockHash);
        //上链信息保存
        fullLogInfo.setId(1);
        fullLogInfo.setChainHash(txHash);
        fullLogInfo.setChainState(1);
        fullLogInfoService.updateFullLogInfoChainHash(fullLogInfo);

        //查询
        TransactionResponse tr = fiscoUtil.searchFullLogChainInfo(fullLogInfo.getIpfsHash(),contractAddress);
        String txJsonStr = JSON.toJSONString(tr);
        System.out.println("链信息查询成功，返回值为："+txJsonStr);
        return Resp.success();
    }

}
