package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.threat.FullLogInfo;
import org.springframework.stereotype.Component;

@Component
public interface FullLogInfoMapper {

    int insertFullLogInfo(FullLogInfo logInfo);

    int updateFullLogInfoChainHash(FullLogInfo fullLogInfo);

}
