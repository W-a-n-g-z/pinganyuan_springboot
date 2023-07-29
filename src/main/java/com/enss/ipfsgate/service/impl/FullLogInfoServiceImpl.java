package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.FullLogInfoMapper;
import com.enss.ipfsgate.model.threat.FullLogInfo;
import com.enss.ipfsgate.service.FullLogInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FullLogInfoServiceImpl implements FullLogInfoService {

    private static final Logger logger = LoggerFactory.getLogger(FullLogInfoServiceImpl.class);

    @Autowired
    private FullLogInfoMapper fullLogInfoMapper;

    @Override
    public int insertFullLogInfo(FullLogInfo fullLogInfo) {
        fullLogInfoMapper.insertFullLogInfo(fullLogInfo);
        return fullLogInfo.getId();
    }

    @Override
    public int updateFullLogInfoChainHash(FullLogInfo fullLogInfo){
        return fullLogInfoMapper.updateFullLogInfoChainHash(fullLogInfo);
    }

}
