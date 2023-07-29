package com.enss.ipfsgate.mapper;

import com.enss.ipfsgate.model.threat.ThreatResult;
import org.springframework.stereotype.Component;

@Component
public interface ThreatMapper {

    //更新已存在威胁的溯源信息
    public int updateThreatTraceHash(ThreatResult threatAnalyInfo);

}
