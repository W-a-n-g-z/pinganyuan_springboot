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

    public static Integer getStrSha256Byte(String str){
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
        System.out.println(Arrays.toString(output));
        return bytes2Int(output);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    //16进制的byte[]转换为10进制的数字
    public static int bytes2Int(byte[] bytes)
    {
        int decimal = 0;
        for (byte b : bytes) {
            decimal = (decimal << 8) | (b & 0xFF);
        }
        return decimal;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        BigInteger bigInteger = new BigInteger(HashUtil.getStrSha256("Hello"), 16);
        System.out.println(bigInteger.toString());
    }

}
