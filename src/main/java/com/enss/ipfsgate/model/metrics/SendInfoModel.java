package com.enss.ipfsgate.model.metrics;

//中心服务器发送指标
public class SendInfoModel {
    //[
    // {
    // "metric":"coral.demo.d", //指标名称（自定义）
    // "endpoint":"192.168.1.15471", //唯一标识
    // "timestamp":{{now}}, //时间戳
    // "step":50, //发送周期
    // "value":0, //值 （暂支持 数字、字符串、数组）
    // "tagsMap": {},
    //
    // }
    // ]

    private String metric;
    private String endpoint;
    private Long timestamp;
    private Integer step;
    private Integer value;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
