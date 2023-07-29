//定时上传文件并上链

package com.enss.ipfsgate.config;

import com.alibaba.fastjson.JSON;
import com.enss.ipfsgate.model.config.AppXmlConfig;
import com.enss.ipfsgate.model.threat.FullLogInfo;
import com.enss.ipfsgate.service.ContractService;
import com.enss.ipfsgate.service.FullLogInfoService;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.fisco.web3j.FiscoUtil;
import com.enss.ipfsgate.utils.ipfs.IPFSClusterUtils;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.math.BigInteger;

/***
 * 应用配置和计划任务
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class AppConfigSchedule {

    private static final Logger logger = LoggerFactory.getLogger(AppConfigSchedule.class);

    @Autowired
    private FullLogInfoService fullLogInfoService;

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

    public static String logCachePath = "";

    public static String year;
    public static String month;
    public static String day;

    public static String oldYear;
    public static String oldMonth;
    public static String oldDay;

    public static String ignoreSrcs= "";

    //链
    public static String sdkIp;
    public static String sdkRpcPort;
    public static String upChainKey = "";

    //隐私
    public static String yinsiServerIp;
    public static int yinsiServerPort;
    public static boolean yinsiServerIsOpen;   //威胁服务是否打开，默认打开

    //威胁服务器
    public static String threatServerIp;

    //加载配置文件信息
    public void initConfig(){
        this.clusterMainUrl = appXmlConfig.clusterMainUrl;
        this.ecAllUrlStr = appXmlConfig.ecAllUrlStr;
        this.ecNums = appXmlConfig.ecNums;
        this.logCachePath = appXmlConfig.logCacheAbsolutePath;
        this.oldYear = appXmlConfig.oldYearTest;
        this.oldMonth = appXmlConfig.oldMonthTest;
        this.oldDay = appXmlConfig.oldDayTest;
        this.ignoreSrcs = appXmlConfig.ignoreSrcs;
        this.upChainKey = appXmlConfig.upChainKey;
        this.yinsiServerIp = appXmlConfig.pyServerIp;
        this.yinsiServerPort = appXmlConfig.pyServerPort;
        this.threatServerIp = appXmlConfig.threatServerIp;
        this.yinsiServerIsOpen = true;
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
        }else{
            String oldDir = this.logCachePath+File.separator+oldYear+"-"+oldMonth+"-"+oldDay;
            File oldDirDir = new File(oldDir);
            if(oldDirDir.exists()){
                //获取上链合约地址
                String contractAddress = contractService.selectContractAddress(1);
                //获取文件夹下所有子文件
                File[] oldDirChildFiles = oldDirDir.listFiles();
                boolean allLogInfoSaveSuccess = true;       //所有文件信息录入数据库成功
                for (int i = 0; i < oldDirChildFiles.length; i++) {
                    //判断不是目录
                    if (!oldDirChildFiles[i].isDirectory()) {
                        IPFSClusterUtils ipfsCluster = new IPFSClusterUtils();
                        Resp r3 =ipfsCluster.IPFSUpload(oldDirChildFiles[i].getAbsolutePath());
                        if(r3.getCode()==0){    //上传成功
                            String childFileName = oldDirChildFiles[i].getName();   //当前子文件名称   类型-ip-年份-月份-天.log
                            FullLogInfo fullLogInfo = new FullLogInfo(childFileName,r3.getData().toString());
                            logger.info("上传到全量存储成功："+childFileName);
                            //信息存入数据库
                            fullLogInfo.setIpfsState(1);
                            int insertResultId = fullLogInfoService.insertFullLogInfo(fullLogInfo);
                            //更新已发现威胁的溯源哈希，节点较少，暂不考虑效率问题
                            if(insertResultId>0){
                                logger.info("全量存储原始文件信息记录成功！");
                                oldDirChildFiles[i].delete();
                            }else{
                                allLogInfoSaveSuccess = false;
                                logger.info("全量存储原始文件信息记录失败！");
                            }
                            fullLogInfo.setId(insertResultId);
                            boolean upChainRet = upChianAndUpdateTx(fullLogInfo,contractAddress);
                            if(upChainRet){
                                logger.info(childFileName+"已执行上链操作！");
                            }
                        }
                    }
                }
                if(allLogInfoSaveSuccess){
                    oldDirDir.delete();
                }
            }else{
                logger.info(oldDirDir.getName()+"无文件生成!");
            }

        }
    }

    /**
     * 日志信息上链并更新数据库的链状态
     * @param fullLogInfo
     * @param contractAddress
     * @return
     */
    public boolean upChianAndUpdateTx(FullLogInfo fullLogInfo,String contractAddress) {
        TransactionResponse transactionResponse = null;
        try {
            transactionResponse = fiscoUtil.upFullLogChainInfo(AppConfigSchedule.upChainKey, fullLogInfo.getIpfsHash(), JSON.toJSONString(fullLogInfo), contractAddress);
        } catch (Exception e) {
            logger.error("上链失败，日志文件为："+fullLogInfo.getLogName());
            return false;
        }
        TransactionReceipt transactionReceipt = transactionResponse.getTransactionReceipt();
        String txHash = transactionReceipt.getTransactionHash();

        String blockNumberStr = transactionReceipt.getBlockNumber().substring(2);
        int blockNumberInteger = Integer.parseInt(blockNumberStr, 16);
        BigInteger blockNumber = new BigInteger("" + blockNumberInteger);
        //上链信息保存
        fullLogInfo.setChainHash(txHash);
        fullLogInfo.setChainState(1);
        fullLogInfo.setChainHeight(blockNumber.toString());
        System.out.println("区块高度："+blockNumber.toString());
        fullLogInfoService.updateFullLogInfoChainHash(fullLogInfo);
        return true;
    }

}
