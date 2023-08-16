package com.enss.ipfsgate.model.repo.vo;

import com.enss.ipfsgate.model.repo.RepoFile;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class RepoFileVo extends RepoFile {

    private String timeDesc;

    public RepoFileVo() {
    }

    public RepoFileVo(Integer id, Integer repoId, Integer branchId, String fileName, String relativePath, Date uploadDate, Integer fileType, String fileTypeName, BigInteger fileSize, String fileSizeStr, String tempSavePath, String tempUrl, String ipfsHash, Integer ipfsState, String chainHash, Integer chainState, String chainHeight, String remark, String timeDesc) {
        super(id, repoId, branchId, fileName, relativePath, uploadDate, fileType, fileTypeName, fileSize, fileSizeStr, tempSavePath, tempUrl, ipfsHash, ipfsState, chainHash, chainState, chainHeight, remark);
        this.timeDesc = timeDesc;
    }

    public String getTimeDesc() {
        this.timeDesc = tranTimeDesc(this.getUploadDate());
        return timeDesc;
    }

    public void setTimeDesc(String timeDesc) {
        this.timeDesc = timeDesc;
    }


    /**
     * 将日期转换为x分钟前，x小时前的格式
     * @param uploadDate
     * @return
     */
    public String tranTimeDesc(Date uploadDate){
        String retStr = "";

        Date nowDate = new Date();
        long uploadMilliSeconds = uploadDate.getTime();
        long nowMilliSeconds = nowDate.getTime();
        long duringMilliSeconds = nowMilliSeconds-uploadMilliSeconds;
        long duringSeconds= duringMilliSeconds/1000;    //到现在的秒数
        if(duringSeconds<60){   //1分钟内
            retStr = "刚刚";
        }else if(duringSeconds>=60 && duringSeconds<3600){
            long min = duringSeconds/60;
            retStr = min+"分钟前";
        }else if(duringSeconds>=3600 && duringSeconds<(3600*24)){
            long hour = duringSeconds/60/60;
            retStr = hour+"小时前";
        }else if(duringSeconds>=(3600*24)){
            long day = duringSeconds/60/60/24;
            retStr = day+"天前";
        }
        return retStr;
    }
}
