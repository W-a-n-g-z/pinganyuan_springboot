package com.enss.ipfsgate.service;

import com.enss.ipfsgate.model.threat.ThreatResult;
import org.springframework.stereotype.Service;

@Service
public interface ThreatService {

    //更新已存在威胁的溯源信息
    public int updateThreatTraceHash(ThreatResult threatResult);

}
