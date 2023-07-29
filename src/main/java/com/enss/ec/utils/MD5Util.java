package com.enss.ec.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * 获取字符串MD5值
	 * @param oldStr
	 * @return
	 */
	private static String GetMD5str(String oldStr) {
		 byte[] hash = null;
		 try {
	        hash = MessageDigest.getInstance("MD5").digest(oldStr.getBytes("UTF-8"));
	     } catch (NoSuchAlgorithmException e1) {
//	        throw new RuntimeException("Huh, MD5 should be supported?", e);
	     } catch (UnsupportedEncodingException e2) {
//	        throw new RuntimeException("Huh, UTF-8 should be supported?", e);
	     }

	    StringBuilder hex = new StringBuilder(hash.length * 2);
	    for (byte b : hash) {
	        if((b&0xFF)<0x10){
	        	hex.append("0");
	        }
	        hex.append(Integer.toHexString(b & 0xFF));
	    }
	    return hex.toString();
	}


	/**
	 * 在2%、20%、40%、65%、85%、95%几个固定位置开始分别截取100个字节
	 * 效率上比较高，2.5G文件大概需要0.5-0.7秒
	 * @param filePath 文件路径
	 * @return
	 */
	public static String getQuickMD5(String filePath) {
		BigDecimal length = new BigDecimal(100);
		File file = new File(filePath);
		long[] starts = {2, 20, 40, 65, 85, 95};
		String value = null;
		
		StringBuffer returnStrFileMD5 = new StringBuffer("");
		try {
			RandomAccessFile rafi = new RandomAccessFile(file, "r");
			FileChannel fci = rafi.getChannel();
			
			for (int j = 0; j < starts.length; j++) {
				long size = length.longValue();
				long checkstart = (long) (file.length() * starts[j] / 100);
				
				while(checkstart>file.length()){
					checkstart = checkstart/10;
				}
				if (file.length()-checkstart < size) {
					size = file.length()-checkstart;
				}
				MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, checkstart, size);
				
				for (int i = 0; i < size; i++) {
		            byte b = mbbi.get(i);
		            returnStrFileMD5.append(b);
				}
			}
			rafi.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		value = GetMD5str(returnStrFileMD5.toString());
		return value;
	}

	public static String getFileMD5(String filePath){

		String ret = "";

		try {
			ret = DigestUtils.md5Hex(new FileInputStream(filePath));
		} catch (IOException e) {
			System.out.println("获取文件MD5信息失败："+filePath);
		}
		return ret;
	}

}
