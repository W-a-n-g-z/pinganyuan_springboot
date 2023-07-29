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

    /**
     * 发布全量日志数据上珊瑚链使用的智能合约，key-value结构
     * key：文件在ipfs存储的hash
     * value：文件整体信息字符串
     * @return 返回区块链收据实体
     */
    public TransactionResponse deployFullLogBase() {
        TransactionResponse response = null;
        try {
            response = transactionProcessor.deployByContractLoader("FullLog", new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
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

    /**
     * 查询珊瑚链数据上链信息
     * @param ipfsHash 文件存储在ipfs的hash
     * @return
     */
    public TransactionResponse searchFullLogChainInfo(String ipfsHash, String contractAddress) throws ABICodecException, TransactionBaseException {
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(ipfsHash);
        // 调用FullLog合约，合约地址为contractAddress， 调用函数名为『get』，函数参数类型为ipfsHash
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(
                "FullLog", contractAddress, "get", paramList);
        return transactionResponse;
    }

    /**
     * 发布威胁处理结果上珊瑚链使用的智能合约，key-value结构
     * key：威胁名称，threat_name
     * value：威胁结论内容，threat_result_content
     * @return 返回区块链收据实体
     */
    public TransactionResponse deployThreatResultBase() {
        TransactionResponse response = null;
        try {
            response = transactionProcessor.deployByContractLoader("ThreatResult", new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 将威胁处理结果上珊瑚链，key-value结构
     * @param priKey 上链用户的私钥
     * @param threat_name 威胁名称，作为key
     * @param threat_result_content 威胁信息的json字符串，作为value
     * @param contractAddress 合约地址
     * @return 返回区块链收据实体
     */
    public TransactionResponse upThreatResult(String priKey, String threat_name, String threat_result_content, String contractAddress) throws Exception {
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(priKey);
        transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(
                coralClient, cryptoKeyPair, "src/main/resources/abi/", "src/main/resources/bin/");
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(threat_name);
        paramList.add(threat_result_content);
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(
                "ThreatResult", contractAddress, "set", paramList);
        return transactionResponse;
    }

    /**
     * 查询珊瑚链威胁处理结果上链信息
     * @param threat_name 威胁名称
     * @return
     */
    public TransactionResponse searchThreatResult(String threat_name, String contractAddress) throws ABICodecException, TransactionBaseException {
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(threat_name);
        // 调用FullLog合约，合约地址为contractAddress， 调用函数名为『get』，函数参数类型为ipfsHash
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(
                "ThreatResult", contractAddress, "get", paramList);
        return transactionResponse;
    }

    /**
     * 发布模型上珊瑚链使用的智能合约，key-value结构
     * key：模型名称，predict_model_name
     * value：模型内容，predict_model_content
     * @return 返回区块链收据实体
     */
    public TransactionResponse deployPredictModelBase() {
        TransactionResponse response = null;
        try {
            response = transactionProcessor.deployByContractLoader("PredictModel", new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 将模型上珊瑚链，key-value结构
     * @param priKey 上链用户的私钥
     * @param predict_model_name 模型名称，作为key
     * @param predict_model_content 模型内容，作为value
     * @param contractAddress 合约地址
     * @return 返回区块链收据实体
     */
    public TransactionResponse upPredictModel(String priKey, String predict_model_name, String predict_model_content, String contractAddress) throws Exception {
        CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(priKey);
        transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(
                coralClient, cryptoKeyPair, "src/main/resources/abi/", "src/main/resources/bin/");
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(predict_model_name);
        paramList.add(predict_model_content);
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(
                "PredictModel", contractAddress, "set", paramList);
        return transactionResponse;
    }

    /**
     * 查询模型上链信息
     * @param predict_model_name 模型名称，作为key
     * @return
     */
    public TransactionResponse searchPredictModel(String predict_model_name, String contractAddress) throws ABICodecException, TransactionBaseException {
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(predict_model_name);
        // 调用FullLog合约，合约地址为contractAddress， 调用函数名为『get』，函数参数类型为ipfsHash
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader(
                "PredictModel", contractAddress, "get", paramList);
        return transactionResponse;
    }




}
