package com.enss.ipfsgate.utils.fisco.web3j;


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
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(priKey);
        AssembleTransactionProcessor assembleTransactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(
                coralClient, cryptoKeyPair, "src/main/resources/abi/", "src/main/resources/bin/");
        TransactionResponse transactionResponse = assembleTransactionProcessor.sendTransactionAndGetResponseByContractLoader(
                contractName, contractAddress, functionName, paramList);
        return transactionResponse;
    }




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

}
