package com.enss.ec;

import com.enss.ec.jerasure.Encoder;
import com.enss.ec.utils.FileUtils;
import com.enss.ipfsgate.model.ipfs.ECModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * EC调度工具类
 */
public class ECUtils {

    private static final Logger logger = LoggerFactory.getLogger(ECUtils.class);

    /***
     * 根据文件路径，将文件分块
     * @return 分块的文件列表存储地址
     */
    public ECModel ecCommand(String filePath, int kNum, int mNum){
        ECModel ecModel = new ECModel(filePath,kNum,mNum);
        List<String> filePathList = new ArrayList<>();

        try {
            Encoder ec = new Encoder(kNum,mNum,8);
            ec.encode(new File(filePath));

            ecModel.setkPartPathMap(getECPartsMap(filePath,"k",kNum));
            ecModel.setmPartPathMap(getECPartsMap(filePath,"m",mNum));
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }

        return ecModel;
    }


    public static Map<String,String> getECPartsMap(String filepath,String partSuffix,int num) {
        Map<String,String> partsPath = new HashMap<String,String>();

        for (int i = 0; i < num; i++) {
            String PartsI = FileUtils.generatePartPath(filepath,partSuffix,i+1);
            //System.out.println(kPartsI);
            int noStr = i+1;
            partsPath.put(Integer.toString(noStr),PartsI);
        }

        return partsPath;

    }


    /***
     * 解析出分片后的文件路径列表
     * @param filepath 源文件路径
     * @param partSuffix k或者m
     * @param num k或者m的数值
     * @return 所有k文件块路径，或者所有m文件块路径
     */
    public List<String> getECPartPathList(String filepath,String partSuffix,int num) {

        List<String> partsPath = new ArrayList<String>();

        for (int i = 0; i < num; i++) {
            partsPath.add(FileUtils.generatePartPath(filepath,partSuffix,i+1));
        }

        return partsPath;
    }

}
