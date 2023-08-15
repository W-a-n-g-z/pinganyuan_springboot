package com.enss.ipfsgate.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class AppXmlConfig {

    @Value("${ipfs.cluster.main.url}")
    public String clusterMainUrl;     //ipfs集群主节点的url

    @Value("${ipfs.ec.allUrlStr}")
    public String ecAllUrlStr;     //ipfs ec节点全部的url

    @Value("${ipfs.ec.num}")
    public Integer ecNums;     //ipfs ec节点数量

    @Value("${fileCachePath}")
    public String fileCachePath;

    @Value("${sdk.ip}")
    public String sdkIp;

    @Value("${sdk.rpcPort}")
    public String sdkRpcPort;

    @Value("${upChainKey}")
    public String upChainKey;

    //节点数量
    @Value("${sdk.num}")
    public Integer sdkNum;
    //节点协议类型,1原共识，2异步共识协议
    @Value("${sdk.type}")
    public Integer sdkType;


}
