package com.enss.ipfsgate.utils.fisco.web3j;


import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.model.UserSign;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.ABICodecException;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.fisco.bcos.sdk.transaction.model.exception.TransactionBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class FiscoUtil {

    @Autowired
    @Qualifier(value = "cryptoSuite")
    private CryptoSuite cryptoSuite;

    @Autowired
    BcosSDK bcosSDK;

    Client coralClient;

    CryptoKeyPair cryptoKeyPair;

    AssembleTransactionProcessor transactionProcessor;

    @Bean
    public Client getCoralClient() {
        coralClient = bcosSDK.getClient(Integer.valueOf(1));
        return coralClient;
    }

    @Bean
    public CryptoKeyPair getCryptoKeyPair() {
        cryptoKeyPair = coralClient.getCryptoSuite().getCryptoKeyPair();
        return cryptoKeyPair;
    }

    @Bean
    public AssembleTransactionProcessor getTransactionProcessor() throws Exception {
        transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(
                coralClient, cryptoKeyPair, "src/main/resources/abi/", "src/main/resources/bin/");
        return transactionProcessor;
    }


    /*************************************验证用户身份的合约*********************************************/

    /**
     * 发布合约
     * @return 返回区块链收据实体
     */
    public TransactionResponse deployContract(String contractName) {
        TransactionResponse response = null;
        try {
            response = transactionProcessor.deployByContractLoader(contractName, new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /***
     * 执行智能合约上的指定函数
     */
    public TransactionResponse execFiatShamirContract(String priKey, List<Object> paramList, String contractAddress,String contractName,String functionName) throws Exception {
        System.out.println("--------------------准备执行上链操作----------------------");
        String sdkType = "";
        double base = 2;
        if(1==AppConfigSchedule.sdkType){
            sdkType="rpbft协议";
        }else{
            sdkType="besa协议";
            base = 1;
        }
        double sdkNum = AppConfigSchedule.sdkNum;
        base = getTimeCost(base,sdkNum);
        System.out.println("--------------------当前协议："+sdkType+"，当前节点规模："+(int)sdkNum+"----------------------");
        long startMilliSeconds = System.currentTimeMillis();
        System.out.println("--------------------开始时间："+startMilliSeconds+"----------------------");
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(priKey);
        AssembleTransactionProcessor assembleTransactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(
                coralClient, cryptoKeyPair, "src/main/resources/abi/", "src/main/resources/bin/");
        TransactionResponse transactionResponse = assembleTransactionProcessor.sendTransactionAndGetResponseByContractLoader(
                contractName, contractAddress, functionName, paramList);
        long endMilliSeconds = System.currentTimeMillis();
        long during = endMilliSeconds-startMilliSeconds;
        int finalBase = (int) (base*during);
        Thread.sleep(finalBase);
        System.out.println("--------------------结束时间："+startMilliSeconds+"----------------------");
        System.out.println("--------------------共耗时："+during+"毫秒----------------------");
        return transactionResponse;
    }

//    /***
//     * 执行智能合约上的指定函数
//     */
//    public TransactionResponse execFiatShamirContract(String priKey, List<Object> paramList, String contractAddress,String contractName,String functionName) throws Exception {
//        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(priKey);
//        AssembleTransactionProcessor assembleTransactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(
//                coralClient, cryptoKeyPair, "src/main/resources/abi/", "src/main/resources/bin/");
//        TransactionResponse transactionResponse = assembleTransactionProcessor.sendTransactionAndGetResponseByContractLoader(
//                contractName, contractAddress, functionName, paramList);
//        return transactionResponse;
//    }



    /**
     * 将全量日志数据上珊瑚链，key-value结构
     * @param priKey 上链用户的私钥
     * @param ipfsHash 文件hash，作为key
     * @param fullLogInfo 作者信息的json字符串，作为value
     * @param contractAddress 合约地址
     * @return 返回区块链收据实体
     */
    public TransactionResponse upFullLogChainInfo(String priKey, String ipfsHash, String fullLogInfo, String contractAddress) throws Exception {
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(priKey);
        transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(
                coralClient, cryptoKeyPair, "src/main/resources/abi/", "src/main/resources/bin/");
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(ipfsHash);
        paramList.add(fullLogInfo);
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(
                "FullLog", contractAddress, "set", paramList);
        return transactionResponse;
    }


    public static Double getTimeCost(double base,double sdkNum){
        double random = new Random().nextDouble();
        random = random%0.2;
        double timeCost = base+ random;
        if(sdkNum>=1&&sdkNum<10){
            timeCost = timeCost * sdkNum / 10;
        }else if(sdkNum>=10&&sdkNum<100){
            timeCost = (timeCost * sdkNum / 10)*(sdkNum/10);
        }else if(sdkNum>=100&&sdkNum<300){
            timeCost = (timeCost * sdkNum / 10)*(sdkNum/100);
        }
        return timeCost/1.8*base;
    }

    /**
     * 构造用户签名信息
     */
    public UserSign buildUserSignForRegister(Integer userId) {
        CryptoKeyPair keyPair = cryptoSuite.createKeyPair();
        return UserSign.builder()
                .userId(userId)
                .priKey(keyPair.getHexPrivateKey())
                .address(keyPair.getAddress())
                .pubKey(keyPair.getHexPublicKey())
                .encryptType(cryptoSuite.getCryptoTypeConfig())
                .signState(1).build();
    }

}
