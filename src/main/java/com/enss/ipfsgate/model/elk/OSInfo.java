package com.enss.ipfsgate.model.elk;

public class OSInfo {

    private String kernel;
    private String codename;
    private String name;
    private String family;
    private String type;
    private String version;
    private String platform;

    public OSInfo() {
    }

    public OSInfo(String kernel, String codename, String name, String family, String type, String version, String platform) {
        this.kernel = kernel;
        this.codename = codename;
        this.name = name;
        this.family = family;
        this.type = type;
        this.version = version;
        this.platform = platform;
    }

    public String getKernel() {
        return kernel;
    }

    public void setKernel(String kernel) {
        this.kernel = kernel;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
