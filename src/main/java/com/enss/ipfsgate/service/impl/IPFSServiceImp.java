package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.service.IPFSService;
import com.enss.ipfsgate.utils.Constant;
import com.enss.ipfsgate.utils.Resp;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class IPFSServiceImp implements IPFSService {

    private static final Logger logger = LoggerFactory.getLogger(IPFSService.class);

    @Override
    public Resp uploadFile(MultipartFile zipFile,String fName) {
        //String targetFilePath = "D:\\test\\uploadTest";
        String targetFilePath = Constant.ROOT + File.separator+"uploadCache";
        //String fileName = UUID.randomUUID().toString().replace("-", "");
        String fileName = fName;
        File targetFile = new File(targetFilePath + File.separator + fileName);
        String fileSavePath = targetFilePath + File.separator + fileName;

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFile);
            IOUtils.copy(zipFile.getInputStream(), fileOutputStream);
            logger.info("------>>>>>>uploaded a file successfully!<<<<<<------");
        } catch (IOException e) {
            return new Resp(1, "文件上传失败");
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return new Resp(0, "文件上传成功",fileSavePath);
    }


}
