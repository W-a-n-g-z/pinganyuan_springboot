package com.enss.ipfsgate.model.ipfs;

import java.util.HashMap;
import java.util.Map;

/***
 * EC时，方便记录文件分块保存信息的实体类
 */
public class ECModel {

    //private List<String> allPartPathList;   //所有块的文件路径
    private Map<String,String> kPartPathMap;     //k文件块的路径
    private Map<String,String> mPartPathMap;     //m文件块的路径
    private int kNum;   //k文件的个数
    private int mNum;   //m文件的个数
    private String sourceFilePath;          //源文件路径


    public ECModel(){

    }

    public ECModel(String sourceFilePath,int kNum,int mNum){
        this.sourceFilePath = sourceFilePath;
        this.kNum = kNum;
        this.mNum = mNum;
    }

//    /***
//     * 将allPartPathList清空，然后将kPartPathList和mPartPathList重新添加
//     */
//    public void calcTotalPath(){
//        if(this.allPartPathList!=null){
//            this.allPartPathList.clear();
//        }
//
//        this.allPartPathList = new ArrayList<>();
//
//        for (int i = 0; i < this.kPartPathList.size() ; i++){
//            this.allPartPathList.add(this.kPartPathList.get(i));
//        }
//
//        for (int i = 0; i < this.mPartPathList.size() ; i++){
//            this.allPartPathList.add(this.mPartPathList.get(i));
//        }
//
//    }
//
//
//    public List<String> getAllPartPathList() {
//        if(null == this.allPartPathList){
//            this.allPartPathList = new ArrayList<String>();
//        }
//        return allPartPathList;
//    }
//
//    public void setAllPartPathList(List<String> allPartPathList) {
//        this.allPartPathList = allPartPathList;
//    }
//
    public Map<String,String> getkPartPathMap() {
        if(null == this.kPartPathMap){
            this.kPartPathMap = new HashMap<String,String>();
        }
        return kPartPathMap;
    }

    public void setkPartPathMap(Map<String,String> kPartPathMap) {
        this.kPartPathMap = kPartPathMap;
    }

    public Map<String,String> getmPartPathMap() {
        if(null == this.mPartPathMap){
            this.mPartPathMap = new HashMap<String,String>();
        }
        return mPartPathMap;
    }

    public void setmPartPathMap(Map<String,String> mPartPathMap) {
        this.mPartPathMap = mPartPathMap;
    }

    public int getkNum() {
        return kNum;
    }

    public void setkNum(int kNum) {
        this.kNum = kNum;
    }

    public int getmNum() {
        return mNum;
    }

    public void setmNum(int mNum) {
        this.mNum = mNum;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }
}
