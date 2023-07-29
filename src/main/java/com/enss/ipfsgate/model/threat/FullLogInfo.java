package com.enss.ipfsgate.model.threat;

import com.enss.ipfsgate.utils.JsonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 全量日志的实体类，由于netlog目前结构一致，也适用与netlog
 */
public class FullLogInfo {

    private Integer id;             //日志id
    private Integer nodeId;         //日志所属的节点id，节点信息表主键
    private String logName;         //文件名
    private Date logDate;           //日志所属日期
    private Integer logType;        //日志类型，日志类型表主键
    private String logTypeName;     //日志类型字符串，通过日志文件名分析而来，防止无法在日志类型表中找到相应id
    private String logSource;       //日志来源IP
    private String ipfsHash;        //日志记录在全量存储中的hash
    private Integer logSize;        //日志大小
    private Integer ipfsState;      //日志存储状态，1.成功 2.失败 3.未知
    private String chainHash;       //上链哈希
    private Integer chainState;     //上链状态，1.成功 2.失败 3.未知
    private String chainHeight;     //区块高度
    private String remark;

    public FullLogInfo(){
    }

    public FullLogInfo(String logName) {
        this.logName = logName;
        String fileNamePart = logName.substring(0,logName.lastIndexOf("."));
        String[] fileInfos = fileNamePart.split("-");
        this.logTypeName = fileInfos[0];
        this.logSource = fileInfos[1];
        String logYear = fileInfos[2];
        String logMonth = fileInfos[3];
        String logDay = fileInfos[4];
        String dateStr = logYear+"-"+logMonth+"-"+logDay;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.logDate = date;
    }

    //传入文件名和上传返回json，自动解析其余信息
    //文件名格式，类型-ip-年份-月份-天.log
    //返回json格式，{"Hash":"QmaaTGhMmzUdu1F5ZH4rvBLL21MfGXi5Poww8oTFNauwbr","Links":[],"Name":"authlog-192.168.1.214-2021-11-17.log","Size":"1255"}
    public FullLogInfo(String logName,String uploadResult) {
        this.logName = logName;
        String fileNamePart = logName.substring(0,logName.lastIndexOf("."));
        String[] fileInfos = fileNamePart.split("-");
        this.logTypeName = fileInfos[0];
        this.logSource = fileInfos[1];
        String logYear = fileInfos[2];
        String logMonth = fileInfos[3];
        String logDay = fileInfos[4];
        String dateStr = logYear+"-"+logMonth+"-"+logDay;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.logDate = date;
        HashMap<String, Object> jsonRetInfo = JsonUtil.jsonToMap(uploadResult);
        this.ipfsHash = jsonRetInfo.get("Hash").toString();
        this.logSize = Integer.parseInt(jsonRetInfo.get("Size").toString());
//        System.out.println("文件哈希为："+jsonRetInfo.get("Hash").toString());
//        System.out.println("文件大小为："+jsonRetInfo.get("Size").toString());
//        System.out.println("文件日期为："+this.logDate.toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getLogTypeName() {
        return logTypeName;
    }

    public void setLogTypeName(String logTypeName) {
        this.logTypeName = logTypeName;
    }

    public String getLogSource() {
        return logSource;
    }

    public void setLogSource(String logSource) {
        this.logSource = logSource;
    }

    public String getIpfsHash() {
        return ipfsHash;
    }

    public void setIpfsHash(String ipfsHash) {
        this.ipfsHash = ipfsHash;
    }

    public Integer getLogSize() {
        return logSize;
    }

    public void setLogSize(Integer logSize) {
        this.logSize = logSize;
    }

    public Integer getIpfsState() {
        return ipfsState;
    }

    public void setIpfsState(Integer ipfsState) {
        this.ipfsState = ipfsState;
    }

    public String getChainHash() {
        return chainHash;
    }

    public void setChainHash(String chainHash) {
        this.chainHash = chainHash;
    }

    public Integer getChainState() {
        return chainState;
    }

    public void setChainState(Integer chainState) {
        this.chainState = chainState;
    }

    public String getChainHeight() {
        return chainHeight;
    }

    public void setChainHeight(String chainHeight) {
        this.chainHeight = chainHeight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
