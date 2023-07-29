package com.enss.ipfsgate.model.elk;

import java.util.List;

public class FilebeatHost {

    //格式示例
//    requestHeader:{"hostname":"ubuntu","os":{"kernel":"5.13.0-30-generic","codename":"focal","name":"Ubuntu","type":"linux","family":"debian","version":"20.04.4 LTS (Focal Fossa)","platform":"ubuntu"},"ip":["192.168.1.143","fe80::2036:b475:b9b2:866b"],"containerized":false,"name":"ubuntu","id":"4125bb1638d64f08b7cfbe2b24d8234d","mac":["00:0c:29:2c:2b:c1"],"architecture":"x86_64"}
    private String hostname;
    private OSInfo os;
    private List<String> ip;
    private Boolean containerized;
    private String name;
    private String id;
    private List<String> mac;
    private String architecture;

    public FilebeatHost() {
    }

    public FilebeatHost(String hostname, OSInfo os, List<String> ip, Boolean containerized, String name, String id, List<String> mac, String architecture) {
        this.hostname = hostname;
        this.os = os;
        this.ip = ip;
        this.containerized = containerized;
        this.name = name;
        this.id = id;
        this.mac = mac;
        this.architecture = architecture;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public OSInfo getOs() {
        return os;
    }

    public void setOs(OSInfo os) {
        this.os = os;
    }

    public List<String> getIp() {
        return ip;
    }

    public void setIp(List<String> ip) {
        this.ip = ip;
    }

    public Boolean getContainerized() {
        return containerized;
    }

    public void setContainerized(Boolean containerized) {
        this.containerized = containerized;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMac() {
        return mac;
    }

    public void setMac(List<String> mac) {
        this.mac = mac;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }
}
