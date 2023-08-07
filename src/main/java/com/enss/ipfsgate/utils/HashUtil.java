package com.enss.ipfsgate.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HashUtil {

    public static String getStrSha256(String str){
        // 获取MessageDigest实例
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 输入需要加密的数据
        byte[] input = str.getBytes();
        // 执行加密
        byte[] output = md.digest(input);
        // 输出加密后的结果
        return bytesToHex(output);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }



    public static void main(String[] args) throws NoSuchAlgorithmException {
        BigInteger bigInteger = new BigInteger(HashUtil.getStrSha256("Hello"), 16);
        System.out.println(bigInteger.toString());
    }

}
