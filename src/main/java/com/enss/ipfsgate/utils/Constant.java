package com.enss.ipfsgate.utils;

public class Constant {

    //不需要过滤URI
    public final static String[] includeUrls = new String[]{"/ipfsgate","/upload","/find","/handleLogin","/handleReg","/handleGetCode", ".css", ".js", ".ico", ".jpg", ".png", ".gif", ".eot", ".svg", ".ttf", ".woff"};

    //阿里云API AccessKey ID
    public final static String ACCESSKEYID = "LTAI4FmoUBbWphbysHPufzhJ";
    //阿里云API Access Key Secret
    public final static String ACCESSKEYSECRET = "IK1hK04E7z5IGL1a4JNQlGQTbMDY2N";

    //项目根目录
    public final static String ROOT = System.getProperty("user.dir");

}
