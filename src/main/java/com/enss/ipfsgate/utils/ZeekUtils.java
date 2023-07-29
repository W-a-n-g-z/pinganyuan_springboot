package com.enss.ipfsgate.utils;

import com.enss.ipfsgate.model.threat.ZeekEnums;
import com.enss.ipfsgate.model.threat.ZeekInfo;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;

public class ZeekUtils {

    //将包含zeek信息的字符串，解析为zeek对象
    public static ZeekInfo convertZeekStr(String zeekInfoStr) throws ParseException {
        String[] zeekInfos = zeekInfoStr.split(",");
        if(zeekInfos.length!=33){
            return null;
        }
        ZeekInfo zeekInfo = new ZeekInfo();
        zeekInfo.setDuration(Integer.parseInt(zeekInfos[0].toString()));
        zeekInfo.setProtocolType(ZeekEnums.ProtocolType.get(zeekInfos[1].toString()));
        zeekInfo.setService(ZeekEnums.ServiceType.get(zeekInfos[2].toString()));
        zeekInfo.setFlag(ZeekEnums.FlagType.get(zeekInfos[3].toString()));
        zeekInfo.setSrcBytes(Integer.parseInt(zeekInfos[4]));
        zeekInfo.setDstBytes(Integer.parseInt(zeekInfos[5]));
        zeekInfo.setLand(Integer.parseInt(zeekInfos[6]));
        zeekInfo.setWrongFragments(Integer.parseInt(zeekInfos[7]));
        zeekInfo.setUrgent(Integer.parseInt(zeekInfos[8]));
        zeekInfo.setConnectCount(Integer.parseInt(zeekInfos[9]));
        zeekInfo.setSrvCount(Integer.parseInt(zeekInfos[10]));
        zeekInfo.setSerrorRate(new BigDecimal(zeekInfos[11]));
        zeekInfo.setSrvSerrorRate(new BigDecimal(zeekInfos[12]));
        zeekInfo.setRerrorRate(new BigDecimal(zeekInfos[13]));
        zeekInfo.setSrvRerrorRate(new BigDecimal(zeekInfos[14]));
        zeekInfo.setSameSrvRate(new BigDecimal(zeekInfos[15]));
        zeekInfo.setDiffSrvRate(new BigDecimal(zeekInfos[16]));
        zeekInfo.setSrvDiffHostRate(new BigDecimal(zeekInfos[17]));
        zeekInfo.setDstHostCount(Integer.parseInt(zeekInfos[18]));
        zeekInfo.setDstHostSrvCount(Integer.parseInt(zeekInfos[19]));
        zeekInfo.setDstHostSameSrvRate(new BigDecimal(zeekInfos[20]));
        zeekInfo.setDstHostDiffSrvRate(new BigDecimal(zeekInfos[21]));
        zeekInfo.setDstHostSameSrcPortRate(new BigDecimal(zeekInfos[22]));
        zeekInfo.setDstHostSrvDiffHostRate(new BigDecimal(zeekInfos[23]));
        zeekInfo.setDstHostSerrorRate(new BigDecimal(zeekInfos[24]));
        zeekInfo.setDstHostSrvSerrorRate(new BigDecimal(zeekInfos[25]));
        zeekInfo.setDstHostRerrorRate(new BigDecimal(zeekInfos[26]));
        zeekInfo.setDstHostSrvRerrorRate(new BigDecimal(zeekInfos[27]));
        //网络流量的ip、端口及时间信息
        zeekInfo.setSrcIp(zeekInfos[28]);
        zeekInfo.setSrcPort(Integer.parseInt(zeekInfos[29]));
        zeekInfo.setDstIp(zeekInfos[30]);
        zeekInfo.setDstPort(Integer.parseInt(zeekInfos[31]));
        zeekInfo.setTimestr(DateUtils.parseDate(convertZeekDate(zeekInfos[32]),  "yyyy-MM-dd HH:mm:ss"));
        return zeekInfo;
    }

    public static String convertZeekDate(String zeekDate){
        if(zeekDate.contains("T")&&zeekDate.indexOf("T")>0){
            return zeekDate.replace("T"," ");
        }else{
            return zeekDate;
        }
    }

    //测试
    public static void main(String[] args) {
        ZeekInfo zeekInfo = null;
        try {
            zeekInfo = ZeekUtils.convertZeekStr("0,udp,other,SF,217,0,0,0,0,2,2,0.00,0.00,0.00,0.00,1.00,0.00,0.00,2,2,1.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,192.168.1.162,55248,239.255.255.250,1900,2022-08-15T19:41:19");
        } catch (ParseException e) {
            System.out.println("类型转换异常！");
            e.printStackTrace();
        }
        if(null == zeekInfo){
            System.out.println("转换失败");
            return;
        }
        System.out.println(zeekInfo.getTimestr());
    }

}
