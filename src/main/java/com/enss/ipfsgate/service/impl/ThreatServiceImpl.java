package com.enss.ipfsgate.service.impl;

import com.enss.ipfsgate.mapper.ThreatMapper;
import com.enss.ipfsgate.model.threat.ThreatResult;
import com.enss.ipfsgate.service.ThreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreatServiceImpl implements ThreatService {

    @Autowired
    private ThreatMapper threatMapper;

    @Override
    public int updateThreatTraceHash(ThreatResult threatResult){
        return threatMapper.updateThreatTraceHash(threatResult);
    }

}
