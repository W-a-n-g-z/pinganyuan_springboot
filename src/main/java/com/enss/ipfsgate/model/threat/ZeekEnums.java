package com.enss.ipfsgate.model.threat;

import java.util.HashMap;
import java.util.Map;

public class ZeekEnums {

    /***
     * 协议种类 protocolType
     */
    public final static Map<String,Integer> ProtocolType = new HashMap<String,Integer>() {{
        put("tcp", 1);put("udp", 2);put("icmp", 3);
    }};

    /***
     * 服务种类 serviceType
     */
    public final static Map<String,Integer> ServiceType = new HashMap<String,Integer>() {{
        put("aol", 1);
        put("auth", 2);
        put("bgp", 3);
        put("courier", 4);
        put("csnet_ns", 5);
        put("ctf", 6);
        put("daytime", 7);
        put("discard", 8);
        put("domain", 9);
        put("domain_u", 10);
        put("echo", 11);
        put("eco_i", 12);
        put("ecr_i", 13);
        put("efs", 14);
        put("exec", 15);
        put("finger", 16);
        put("ftp", 17);
        put("ftp_data", 18);
        put("gopher", 19);
        put("harvest", 20);
        put("hostnames", 21);
        put("http", 22);
        put("http_2784", 23);
        put("http_443", 24);
        put("http_8001", 25);
        put("imap4", 26);
        put("IRC", 27);
        put("iso_tsap", 28);
        put("klogin", 29);
        put("kshell", 30);
        put("ldap", 31);
        put("link", 32);
        put("login", 33);
        put("mtp", 34);
        put("name", 35);
        put("netbios_dgm", 36);
        put("netbios_ns", 37);
        put("netbios_ssn", 38);
        put("netstat", 39);
        put("nnsp", 40);
        put("nntp", 41);
        put("ntp_u", 42);
        put("other", 43);
        put("pm_dump", 44);
        put("pop_2", 45);
        put("pop_3", 46);
        put("printer", 47);
        put("private", 48);
        put("red_i", 49);
        put("remote_job", 50);
        put("rje", 51);
        put("shell", 52);
        put("smtp", 53);
        put("sql_net", 54);
        put("ssh", 55);
        put("sunrpc", 56);
        put("supdup", 57);
        put("systat", 58);
        put("telnet", 59);
        put("tftp_u", 60);
        put("tim_i", 61);
        put("time", 62);
        put("urh_i", 63);
        put("urp_i", 64);
        put("uucp", 65);
        put("uucp_path", 66);
        put("vmnet", 67);
        put("whois", 68);
        put("X11", 69);
        put("Z39_50", 70);
    }};

    /***
     * 标记种类 flagType，注意大写
     */
    public final static Map<String,Integer> FlagType = new HashMap<String,Integer>() {{
        put("OTH", 1);put("REJ", 2);put("RSTO", 3);
        put("RSTOS0", 4);put("RSTR", 5);put("S0", 6);
        put("S1", 7);put("S2", 8);put("S3", 9);
        put("SF", 10);put("SH", 11);
    }};

}
