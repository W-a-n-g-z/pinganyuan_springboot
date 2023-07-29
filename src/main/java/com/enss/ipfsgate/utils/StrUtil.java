package com.enss.ipfsgate.utils;

import org.springframework.stereotype.Component;

//读取外部配置文件信息的工具类
@Component
public class StrUtil {

    public static String[] convertStringToArray(String valueStr){
        String[] retArray = valueStr.split(",");
        return retArray;
    }

}
