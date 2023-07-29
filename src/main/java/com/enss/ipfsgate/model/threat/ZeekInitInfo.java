package com.enss.ipfsgate.model.threat;

import java.math.BigDecimal;
import java.util.Date;

//zeek转换前的数据格式，部分属性为枚举值
public class ZeekInitInfo {

    private Integer id;
    //网络流量的28项基本特征
    private Integer	duration;
    private String	protocoType;
    private String	service;
    private String	flag;
    private Integer	srcBytes;
    private Integer	dstBytes;
    private Integer	land;
    private Integer	wrongFragments;
    private Integer	urgent;
    private Integer	connectCount;
    private Integer	srvCount;
    private BigDecimal serrorRate;
    private BigDecimal srvSerrorRate;
    private BigDecimal rerrorRate;
    private BigDecimal srvRerrorRate;
    private BigDecimal sameSrvRate;
    private BigDecimal diffSrvRate;
    private BigDecimal srvDiffHostRate;
    private Integer dstHostCount;
    private Integer dstHostSrvCount;
    private BigDecimal dstHostSameSrvRate;
    private BigDecimal dstHostDiffSrvRate;
    private BigDecimal dstHostSameSrcPortRate;
    private BigDecimal dstHostSrvDiffHostRate;
    private BigDecimal dstHostSerrorRate;
    private BigDecimal dstHostSrvSerrorRate;
    private BigDecimal dstHostRerrorRate;
    private BigDecimal dstHostSrvRerrorRate;
    //网络流量的ip、端口及时间信息
    private String srcIp;
    private Integer srcPort;
    private String dstIp;
    private Integer dstPort;
    private Date timestr;

    public ZeekInitInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getProtocoType() {
        return protocoType;
    }

    public void setProtocoType(String protocoType) {
        this.protocoType = protocoType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getSrcBytes() {
        return srcBytes;
    }

    public void setSrcBytes(Integer srcBytes) {
        this.srcBytes = srcBytes;
    }

    public Integer getDstBytes() {
        return dstBytes;
    }

    public void setDstBytes(Integer dstBytes) {
        this.dstBytes = dstBytes;
    }

    public Integer getLand() {
        return land;
    }

    public void setLand(Integer land) {
        this.land = land;
    }

    public Integer getWrongFragments() {
        return wrongFragments;
    }

    public void setWrongFragments(Integer wrongFragments) {
        this.wrongFragments = wrongFragments;
    }

    public Integer getUrgent() {
        return urgent;
    }

    public void setUrgent(Integer urgent) {
        this.urgent = urgent;
    }

    public Integer getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(Integer connectCount) {
        this.connectCount = connectCount;
    }

    public Integer getSrvCount() {
        return srvCount;
    }

    public void setSrvCount(Integer srvCount) {
        this.srvCount = srvCount;
    }

    public BigDecimal getSerrorRate() {
        return serrorRate;
    }

    public void setSerrorRate(BigDecimal serrorRate) {
        this.serrorRate = serrorRate;
    }

    public BigDecimal getSrvSerrorRate() {
        return srvSerrorRate;
    }

    public void setSrvSerrorRate(BigDecimal srvSerrorRate) {
        this.srvSerrorRate = srvSerrorRate;
    }

    public BigDecimal getRerrorRate() {
        return rerrorRate;
    }

    public void setRerrorRate(BigDecimal rerrorRate) {
        this.rerrorRate = rerrorRate;
    }

    public BigDecimal getSrvRerrorRate() {
        return srvRerrorRate;
    }

    public void setSrvRerrorRate(BigDecimal srvRerrorRate) {
        this.srvRerrorRate = srvRerrorRate;
    }

    public BigDecimal getSameSrvRate() {
        return sameSrvRate;
    }

    public void setSameSrvRate(BigDecimal sameSrvRate) {
        this.sameSrvRate = sameSrvRate;
    }

    public BigDecimal getDiffSrvRate() {
        return diffSrvRate;
    }

    public void setDiffSrvRate(BigDecimal diffSrvRate) {
        this.diffSrvRate = diffSrvRate;
    }

    public BigDecimal getSrvDiffHostRate() {
        return srvDiffHostRate;
    }

    public void setSrvDiffHostRate(BigDecimal srvDiffHostRate) {
        this.srvDiffHostRate = srvDiffHostRate;
    }

    public Integer getDstHostCount() {
        return dstHostCount;
    }

    public void setDstHostCount(Integer dstHostCount) {
        this.dstHostCount = dstHostCount;
    }

    public Integer getDstHostSrvCount() {
        return dstHostSrvCount;
    }

    public void setDstHostSrvCount(Integer dstHostSrvCount) {
        this.dstHostSrvCount = dstHostSrvCount;
    }

    public BigDecimal getDstHostSameSrvRate() {
        return dstHostSameSrvRate;
    }

    public void setDstHostSameSrvRate(BigDecimal dstHostSameSrvRate) {
        this.dstHostSameSrvRate = dstHostSameSrvRate;
    }

    public BigDecimal getDstHostDiffSrvRate() {
        return dstHostDiffSrvRate;
    }

    public void setDstHostDiffSrvRate(BigDecimal dstHostDiffSrvRate) {
        this.dstHostDiffSrvRate = dstHostDiffSrvRate;
    }

    public BigDecimal getDstHostSameSrcPortRate() {
        return dstHostSameSrcPortRate;
    }

    public void setDstHostSameSrcPortRate(BigDecimal dstHostSameSrcPortRate) {
        this.dstHostSameSrcPortRate = dstHostSameSrcPortRate;
    }

    public BigDecimal getDstHostSrvDiffHostRate() {
        return dstHostSrvDiffHostRate;
    }

    public void setDstHostSrvDiffHostRate(BigDecimal dstHostSrvDiffHostRate) {
        this.dstHostSrvDiffHostRate = dstHostSrvDiffHostRate;
    }

    public BigDecimal getDstHostSerrorRate() {
        return dstHostSerrorRate;
    }

    public void setDstHostSerrorRate(BigDecimal dstHostSerrorRate) {
        this.dstHostSerrorRate = dstHostSerrorRate;
    }

    public BigDecimal getDstHostSrvSerrorRate() {
        return dstHostSrvSerrorRate;
    }

    public void setDstHostSrvSerrorRate(BigDecimal dstHostSrvSerrorRate) {
        this.dstHostSrvSerrorRate = dstHostSrvSerrorRate;
    }

    public BigDecimal getDstHostRerrorRate() {
        return dstHostRerrorRate;
    }

    public void setDstHostRerrorRate(BigDecimal dstHostRerrorRate) {
        this.dstHostRerrorRate = dstHostRerrorRate;
    }

    public BigDecimal getDstHostSrvRerrorRate() {
        return dstHostSrvRerrorRate;
    }

    public void setDstHostSrvRerrorRate(BigDecimal dstHostSrvRerrorRate) {
        this.dstHostSrvRerrorRate = dstHostSrvRerrorRate;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public Integer getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Integer srcPort) {
        this.srcPort = srcPort;
    }

    public String getDstIp() {
        return dstIp;
    }

    public void setDstIp(String dstIp) {
        this.dstIp = dstIp;
    }

    public Integer getDstPort() {
        return dstPort;
    }

    public void setDstPort(Integer dstPort) {
        this.dstPort = dstPort;
    }

    public Date getTimestr() {
        return timestr;
    }

    public void setTimestr(Date timestr) {
        this.timestr = timestr;
    }
}
