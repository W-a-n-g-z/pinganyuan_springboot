package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.threat.FullLogInfo;
import org.springframework.stereotype.Service;

@Service
public interface FullLogInfoService {

    //插入日志的基本信息
    public int insertFullLogInfo(FullLogInfo fullLogInfo);

    //补充更新日志的上链hash
    public int updateFullLogInfoChainHash(FullLogInfo fullLogInfo);

}
