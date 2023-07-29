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

    @Value("${LogCacheAbsolutePath}")
    public String logCacheAbsolutePath;

    @Value("${OldYearTest}")
    public String oldYearTest;

    @Value("${OldMonthTest}")
    public String oldMonthTest;

    @Value("${OldDayTest}")
    public String oldDayTest;

    @Value("${ignoreSrcs}")
    public String ignoreSrcs;

    @Value("${sdk.ip}")
    public String sdkIp;

    @Value("${sdk.rpcPort}")
    public String sdkRpcPort;

    @Value("${upChainKey}")
    public String upChainKey;

    @Value("${pyServerIp}")
    public String pyServerIp;

    @Value("${pyServerPort}")
    public int pyServerPort;

    @Value("${threatServerIp}")
    public String threatServerIp;

}
