package com.enss.ipfsgate.service;

import com.enss.ipfsgate.utils.Resp;
import org.springframework.web.multipart.MultipartFile;

public interface IPFSService {

    public Resp uploadFile(MultipartFile zipFile,String fName);

}
