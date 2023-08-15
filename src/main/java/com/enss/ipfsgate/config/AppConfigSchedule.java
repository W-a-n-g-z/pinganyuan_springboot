//定时上传文件并上链

package com.enss.ipfsgate.config;

import com.alibaba.fastjson.JSON;
import com.enss.ipfsgate.model.config.AppXmlConfig;
import com.enss.ipfsgate.model.threat.RepoFile;
import com.enss.ipfsgate.service.ContractService;
import com.enss.ipfsgate.service.FileService;
import com.enss.ipfsgate.utils.JsonUtil;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.fisco.web3j.FiscoUtil;
import com.enss.ipfsgate.utils.ipfs.IPFSClusterUtils;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;

/***
 * 应用配置和计划任务
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class AppConfigSchedule {

    private static final Logger logger = LoggerFactory.getLogger(AppConfigSchedule.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private FiscoUtil fiscoUtil;


    @Autowired
    AppXmlConfig appXmlConfig;

    public boolean isConfig = false;

    public static String clusterMainUrl;
    public static String ecAllUrlStr;
    public static Integer ecNums;

    //链
    public static String sdkIp;
    public static String sdkRpcPort;
    public static String upChainKey = "";

    public static Integer sdkNum;
    //节点协议类型,1原共识，2异步共识协议
    public static Integer sdkType;

    public static String fileCachePath = "";
    public static String DataFileBasePath;

    //加载配置文件信息
    public void initConfig(){
        AppConfigSchedule.clusterMainUrl = appXmlConfig.clusterMainUrl;
        AppConfigSchedule.ecAllUrlStr = appXmlConfig.ecAllUrlStr;
        AppConfigSchedule.ecNums = appXmlConfig.ecNums;
        AppConfigSchedule.fileCachePath = appXmlConfig.fileCachePath;
        AppConfigSchedule.upChainKey = appXmlConfig.upChainKey;
        AppConfigSchedule.sdkNum = appXmlConfig.sdkNum;
        AppConfigSchedule.sdkType = appXmlConfig.sdkType;
        this.isConfig=true;
        logger.info("加载配置文件成功！");
    }

    //3.添加定时任务
    @Scheduled(cron = "0/10 * * * * ?")
    //或直接指定时间间隔，例如：10秒
    //@Scheduled(fixedRate=10000)
    public void sendInfo(){
        if(!this.isConfig){      //第一次时，加载配置
            initConfig();
        }
    }



}
