package com.enss.ipfsgate.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;

//计算、转换各类文件大小，暂不启用
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /***
     * 将以b为单位的文件大小，自适应转为合适的字符串
     * @param filesize
     * @return
     */
    public static String sizeToStr(BigInteger filesize) {
        String filesizeStr = String.valueOf(filesize);
        BigDecimal sizeNum = new BigDecimal("1024");
        String suff = "字节"; //后缀，初始为字节
        BigDecimal calcSize = new BigDecimal(filesizeStr);
        String ret = "";
        if (calcSize.equals(calcSize.max(sizeNum))) {     //filesize大于1024b
            suff = "KB";
            BigDecimal sizeResult = calcSize.divide(sizeNum);
            if (sizeResult.equals(sizeResult.max(sizeNum))) {     //filesize大于1024KB
                suff = "MB";
                sizeResult = sizeResult.divide(sizeNum);
                if (sizeResult.equals(sizeResult.max(sizeNum))) {     //filesize大于1024MB
                    suff = "GB";
                    sizeResult = sizeResult.divide(sizeNum);
                    ret = String.valueOf(sizeResult);
                } else {
                    ret = String.valueOf(sizeResult);
                }
            } else {
                ret = String.valueOf(sizeResult);
            }
        } else {
            ret = String.valueOf(filesize);
        }
        if (ret.length() - ret.indexOf(".") > 2) {
            ret = ret.substring(0, ret.indexOf(".") + 2);
        }
        ret = ret + suff;
        return ret;
    }

    /***
     * 将以b为单位的文件大小，指定单位，转为合适的字符串
     * @param filesize 文件大小，以b为单位
     * @param suffStr 大小单位，b,k,m,g
     * @return
     */
    public static String sizeToStr(BigInteger filesize, String suffStr) {
        String filesizeStr = String.valueOf(filesize);
        BigDecimal sizeNum = new BigDecimal("1024");
        String suff = ""; //后缀
        BigDecimal calcSize = new BigDecimal(filesizeStr);
        String ret = "";
        if ("b".equals(suffStr)) {
            suff = "字节";
            ret = filesizeStr;
        } else if ("k".equals(suffStr)) {
            suff = "KB";
            ret = String.valueOf(calcSize.divide(sizeNum));
        } else if ("m".equals(suffStr)) {
            suff = "MB";
            ret = String.valueOf(calcSize.divide(sizeNum).divide(sizeNum));
        } else if ("g".equals(suffStr)) {
            suff = "GB";
            ret = String.valueOf(calcSize.divide(sizeNum).divide(sizeNum).divide(sizeNum));
        }
        if (ret.length() - ret.indexOf(".") > 2) {
            ret = ret.substring(0, ret.indexOf(".") + 2);
        }
        ret = ret + suff;
        return ret;
    }

    /***
     * 根据文件后缀名，返回文件类型ID
     * @return
     */
    public static Integer judgeFileTypeBySuffix(String suffix) {
        if (null == suffix || "".equals(suffix) || "" == suffix) {
            return 6;
        }
        String suffixLower = suffix.trim().replace(".", "").toLowerCase();
        if ("xlsx".equals(suffixLower) || "xls".equals(suffixLower)) {
            return 1;
        } else if ("csv".equals(suffixLower)) {
            return 2;
        } else if ("doc".equals(suffixLower) || "txt".equals(suffixLower) || "pdf".equals(suffixLower) || "docx".equals(suffixLower) || "ceb".equals(suffixLower)) {
            return 3;
        } else if ("7z".equals(suffixLower) || "rar".equals(suffixLower) || "zip".equals(suffixLower) || "cab".equals(suffixLower) || "tar".equals(suffixLower) || "gz".equals(suffixLower)) {
            return 4;
        } else {
            return 5;
        }
//        if("xlsx".equals(suffixLower)||"xls".equals(suffixLower)){
//            return 1;
//        }
//        else if(
//            "png".equals(suffixLower)||"jpg".equals(suffixLower)||"gif".equals(suffixLower)||"bmp".equals(suffixLower)
//            ||"tif".equals(suffixLower)||"pcx".equals(suffixLower)||"tga".equals(suffixLower)||"exif".equals(suffixLower)
//            ||"fpx".equals(suffixLower)||"svg".equals(suffixLower)||"psd".equals(suffixLower)||"cdr".equals(suffixLower)
//            ||"pcd".equals(suffixLower)||"dxf".equals(suffixLower)||"ufo".equals(suffixLower)||"eps".equals(suffixLower)
//            ||"ai".equals(suffixLower)||"raw".equals(suffixLower)||"wmf".equals(suffixLower)||"webp".equals(suffixLower)
//            ||"avif".equals(suffixLower)||"apng".equals(suffixLower))
//        {
//            return 2;
//        }else if("doc".equals(suffixLower)||"txt".equals(suffixLower)||"pdf".equals(suffixLower)||"docx".equals(suffixLower)||"ceb".equals(suffixLower)){
//            return 3;
//        }else if("cda".equals(suffixLower)||"wav".equals(suffixLower)||"aiff".equals(suffixLower)||"mp3".equals(suffixLower)){
//            return 4;
//        }else if("flv".equals(suffixLower)||"mp4".equals(suffixLower)||"avi".equals(suffixLower)||"wmv".equals(suffixLower)||"rmvb".equals(suffixLower)){
//            return 5;
//        }else{
//            return 6;
//        }
    }

    // An highlighted block
    public static void approvalFile(MultipartFile filecontent, String path) {
        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            inputStream = filecontent.getInputStream();
            fileName = filecontent.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + "MyFileName.png");
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getMd5(MultipartFile file) {
        try {
            //获取文件的byte信息
            byte[] uploadBytes = file.getBytes();
            // 拿到一个MD5转换器
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            //转换为16进制
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFolder(String folder) {
        File file = new File(folder);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File ziFile : files) {
                    deleteFolder(ziFile.getPath());
                }
            }
        }
        file.delete();
    }

}
