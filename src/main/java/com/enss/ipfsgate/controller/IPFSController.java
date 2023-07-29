//采集日志，缓存在本地

package com.enss.ipfsgate.controller;

import com.alibaba.fastjson.JSON;
import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.model.threat.ThreatAnalyInfo;
import com.enss.ipfsgate.model.threat.ZeekInfo;
import com.enss.ipfsgate.utils.python.PythonNet;
import com.enss.ipfsgate.utils.network.NetworkUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.enss.ipfsgate.utils.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/ipfsgate")
public class IPFSController {

    private static final Logger logger = LoggerFactory.getLogger(IPFSController.class);

    /**
     * 接收logstash传送的日志内容
     * @return
     */
    @RequestMapping("/logstash/alllog")
    public Resp receiveLogstash(@RequestParam String logsource,@RequestParam String logtype, @RequestBody String requestbody){
        //整体思路： 最终格式   类型-ip-年份-月份-天.log
        //每次requestbody包含一行日志内容
        //根据ip创建机器文件夹
        //根据类型创建类型文件夹
        //根据日期创建年份文件夹，月份文件夹，不存在则创建
        //判断每条日期的时间，是否已经存在文件，不存在则创建
        //以追加的形式，追加到日志末尾
        //在定时器中，记录当前日期，获取系统日期，和记录的日期比对，当天数变化的那一次，将之前日期的文件存入ipfs，并删除
        //记录ipfs中原文件名和哈希名之间的关系
        while(null== AppConfigSchedule.year){ //防止启动服务后，过快接收了远端日志，如果不加以等待，会出现null-null-null文件夹
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.info("远端日志接收过快！这个过程可能会导致一些意外情况......");
                logger.error("意外错误："+e.getMessage());
            }
        }
        System.out.println("logtype:"+logtype+",logsource:"+logsource);
        String dirName = AppConfigSchedule.logCachePath+File.separator+ AppConfigSchedule.year+"-"+ AppConfigSchedule.month+"-"+ AppConfigSchedule.day;
        String fileName = dirName+File.separator+logtype+"-"+logsource+"-"+ AppConfigSchedule.year+"-"+ AppConfigSchedule.month+"-"+ AppConfigSchedule.day+".log";
        File dir = new File(dirName);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("创建文件失败："+fileName+"/r/t失败原因："+e.getMessage());
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            requestbody =requestbody +System.getProperty("line.separator");
            fileWriter.write(requestbody);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.error("写入文件失败："+fileName+"/r/t失败原因："+e.getMessage());
            logger.info("未写入的内容："+requestbody);
        }
        return Resp.success();
    }

    /**
     * 接收logstash传送的日志内容
     * @return
     */
    @RequestMapping("/logstash/netlog")
    public Resp receiveLogstashNetLog(@RequestParam String logsource,@RequestParam String logtype, @RequestBody String requestbody){
        //@RequestHeader("X-My-Header") String filebeatHost,
        //整体思路：根据隐私计算判断该行日志的安全性，如果安全则无操作，如果危险则形成 报文-结论-时间戳 的格式
        //文件格式   类型-ip-年份-月份-天.log
        //每次requestbody包含一行日志内容
        //初始为非危险状态，根据隐私计算的结果，判断是否更改危险状态
        if(null== AppConfigSchedule.yinsiServerIp){
            //logger.info("程序正在加载配置，暂时停止分析...");
            //此时python的地址端口还未加载，暂停分析
            return Resp.success();
        }
        //判断目前隐私计算是否是启动状态，如果不是则直接return
        if(!AppConfigSchedule.yinsiServerIsOpen){
            return Resp.success();
        }

        boolean isDanger = false;
        ZeekInfo zeekInfo = null;   //报文转换的对象，与隐私计算结合时传入SDK中
        try {
            zeekInfo = ZeekUtils.convertZeekStr(requestbody);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("网络流量报文转换异常，请检查报文格式...");
        }
        if(null==zeekInfo){
            logger.error("网络流量报文转换异常，未满足33个字段...");
            return Resp.error("网络流量报文转换异常，未满足33个字段...");
        }
        //从安全ip池里比对，如果安全ip则无需分析
        if(null==zeekInfo.getSrcIp()|| AppConfigSchedule.ignoreSrcs.contains(zeekInfo.getSrcIp())){
            return Resp.success();
        }

        //通过网络访问调用隐私计算，接收计算结果
        float yinsiResult = PythonNet.pythonNetExec(1,zeekInfo.toYinsiArray());
        //System.out.println("yinsiResult:"+yinsiResult);
        //float yinsiResult = 0;
        //logger.info("隐私计算结果："+yinsiResult);
        if(-1==yinsiResult){
            logger.info("1条报文计算异常，报文内容："+zeekInfo.toString());
            return Resp.success();
        }
        if(yinsiResult>0.8){
//            System.out.println("发现一条异常流量："+zeekInfo.toString());
//            String percentStr = yinsiResult*100+"%";
//            System.out.println("该流量有问题的概率为："+percentStr);
            isDanger = true;
        }
        //隐私计算
        if(!isDanger){      //非危险状态，直接跳过该日志
            return Resp.success();
        }
        //危险状态，记录该条流量日志
        while(null== AppConfigSchedule.year){ //防止启动服务后，过快接收了远端日志，如果不加以等待，会出现null-null-null文件夹
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.info("远端日志接收过快！这个过程可能会导致一些意外情况......");
                logger.error("意外错误："+e.getMessage());
            }
        }
        String dirName = AppConfigSchedule.logCachePath+File.separator+ AppConfigSchedule.year+"-"+ AppConfigSchedule.month+"-"+ AppConfigSchedule.day;
        String fileName = dirName+File.separator+logtype+"-"+logsource+"-"+ AppConfigSchedule.year+"-"+ AppConfigSchedule.month+"-"+ AppConfigSchedule.day+".log";
        File dir = new File(dirName);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("创建文件失败："+fileName+"/r/t失败原因："+e.getMessage());
            }
        }
        //将日志内容拼接结论以及时间戳
        StringBuilder writeResult = new StringBuilder(); //拼凑可读日志
        writeResult.append("--------------流量异常--------------"+System.getProperty("line.separator"));
        writeResult.append("日志来源："+logsource);
        writeResult.append("隐私计算报文："+requestbody+System.getProperty("line.separator"));
        writeResult.append("原始报文："+requestbody+System.getProperty("line.separator"));
        writeResult.append("异常概率："+yinsiResult*100+"%"+System.getProperty("line.separator"));
        writeResult.append("发现时间："+DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")+System.getProperty("line.separator"));

        requestbody = requestbody+"--"+"该流量有问题的概率为："+yinsiResult*100+"%"+"--" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        //logger.debug("requestbody："+requestbody);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            requestbody =requestbody +System.getProperty("line.separator");
//            fileWriter.write(requestbody);
            fileWriter.write(writeResult.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            logger.error("写入文件失败："+fileName+"/r/t失败原因："+e.getMessage());
            logger.info("未写入的内容："+requestbody);
        }
        //通知威胁处理服务器
        ThreatAnalyInfo threatAnalyInfo = new ThreatAnalyInfo();
        threatAnalyInfo.setAnalyType("zeek");
        threatAnalyInfo.setThreatDesc(requestbody);
        threatAnalyInfo.setNodeIp(logsource);
        threatAnalyInfo.setThreatIp(zeekInfo.getSrcIp());
        threatAnalyInfo.setTraceFileName(logtype+"-"+logsource+"-"+ AppConfigSchedule.year+"-"+ AppConfigSchedule.month+"-"+ AppConfigSchedule.day+".log");
        threatAnalyInfo.setTraceIpfsHash("暂无");
        NetworkUtil.netPost("http://"+ AppConfigSchedule.threatServerIp+":8880/threatMonitor/zeekAlert",JSON.toJSONString(threatAnalyInfo));
        return Resp.success();
    }

    /**
     * 接收elastalert的报警提醒
     * @return
     */
    @RequestMapping("/elastalert")
    public Resp receiveElastalert(@RequestBody String body) {
        System.out.println("告警触发成功！body = "+body);
        return Resp.success();
    }

}
