package com.enss.ipfsgate.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.WalletFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, Class<T> cla) {
        return JSON.parseObject(json, cla);
    }


    public static <T> List<T> toList(String json, Class<T> t) {
        return JSON.parseArray(json, t);
    }

    /**
     * json字符串转map集合
     *
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> jsonToMap(String jsonStr) {
        return JSON.parseObject(jsonStr, new HashMap<String, String>().getClass());
    }

    /**
     * map转json字符串
     *
     * @param map
     * @return
     */
    public static String mapToJson(Map<String, Object> map) {
        String jsonStr = JSON.toJSONString(map);
        return jsonStr;
    }

    /**
     * json字符串转换成对象
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T jsonToBean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /***
     * 将私钥内容转换成对象
     * @param json
     * @return
     */
    public static WalletFile jsonToWallFile(String json){
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSONObject o2 = jsonObject.getJSONObject("crypto");
            WalletFile.ScryptKdfParams ws = JSON.parseObject(String.valueOf(o2.getJSONObject("kdfparams")),WalletFile.ScryptKdfParams.class);
            WalletFile.CipherParams wc = JSON.parseObject(String.valueOf(o2.getJSONObject("cipherparams")),WalletFile.CipherParams.class);
            WalletFile.Crypto wfc = new WalletFile.Crypto();
            wfc.setKdfparams(ws);
            wfc.setKdf(o2.getString("kdf"));
            wfc.setCipher(o2.getString("cipher"));
            wfc.setMac(o2.getString("mac"));
            wfc.setCiphertext(o2.getString("ciphertext"));
            wfc.setCipherparams(wc);
            WalletFile w = new WalletFile();
            w.setCrypto(wfc);
            w.setCryptoV1(wfc);
            w.setAddress(jsonObject.getString("address"));
            w.setId(jsonObject.getString("id"));
            w.setVersion(jsonObject.getInteger("version"));
            return w;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String beanToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json字符串转换成List集合
     * (需要实体类)
     *
     * @param jsonString
     * @return
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> cls) {
        List<T> list = null;
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json字符串转换成ArrayList集合
     * (需要实体类)
     *
     * @param jsonString
     * @return
     */
    public static <T> ArrayList<T> jsonToArrayList(String jsonString, Class<T> cls) {
        ArrayList<T> list = null;
        try {
            list = (ArrayList<T>) JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * List集合转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String listToJson(Object obj) {
        return JSONArray.toJSONString(obj, true);
    }

    /**
     * json转List
     * (不需要实体类)
     *
     * @param jsonStr
     * @return
     */
    public static JSONArray jsonToList(String jsonStr) {
        return JSON.parseArray(jsonStr);
    }


}
