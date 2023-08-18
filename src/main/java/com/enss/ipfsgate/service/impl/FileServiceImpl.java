package com.enss.ipfsgate.service.impl;

import com.alibaba.fastjson.JSON;
import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.mapper.FileMapper;
import com.enss.ipfsgate.model.FileInfo;
import com.enss.ipfsgate.model.repo.RepoFile;
import com.enss.ipfsgate.model.repo.vo.RepoFileVo;
import com.enss.ipfsgate.service.ContractService;
import com.enss.ipfsgate.service.FileService;
import com.enss.ipfsgate.utils.JsonUtil;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.fisco.web3j.FiscoUtil;
import com.enss.ipfsgate.utils.ipfs.IPFSClusterUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ContractService contractService;
    @Autowired
    private FiscoUtil fiscoUtil;

    @Override
    public List<FileInfo> search(String repo_name, String file_name, String file_path) {
        return fileMapper.search(repo_name, file_name, file_path);
    }

    @Override
    public List<FileInfo> searchrepo(String repo_name) {
        return fileMapper.searchrepo(repo_name);
    }

    @Override
    public List<Map<String, Object>> selectAll() {
        return fileMapper.selectAll();
    }

    @Override
    public List<RepoFileVo> searchUnauditedFile() {
        return fileMapper.searchUnauditedFile();
    }

    @Override
    public int searchUnauditedFileCount() {
        return fileMapper.searchUnauditedFileCount();
    }

    @Override
    public int insert(FileInfo record) {
        return fileMapper.insert(record);
    }

    //新增文件信息
    @Override
    public int insertRepoFile(RepoFile repoFile) {
        fileMapper.insertRepoFile(repoFile);
        return repoFile.getId();
    }

    //更新链哈希
    @Override
    public int updateChainHash(RepoFile repoFile) {
        return fileMapper.updateChainHash(repoFile);
    }

    //更新临时存储路径
    @Override
    public int updateTempSavePath(RepoFile repoFile) {
        return fileMapper.updateTempSavePath(repoFile);
    }

    //更新对外提供访问的临时url，暂时可能没用
    @Override
    public int updateTempUrl(RepoFile repoFile) {
        return fileMapper.updateTempUrl(repoFile);
    }

    /***
     * 文件上传ipfs、信息上链，并更新数据库
     * @param repoFile
     */
    @Override
    public Resp uploadIpfsAndChain(RepoFile repoFile){
        //获取上链合约地址
        String contractAddress = contractService.selectContractAddress(2);
        boolean ipfsSuccess = true;       //文件上传ipfs成功
        boolean chainSuccess = true;       //文件信息录入数据库成功
        File uploadFile = new File(repoFile.getTempSavePath());
        //判断不是目录，且文件存在
        if (!uploadFile.isDirectory()&&uploadFile.exists()) {
            IPFSClusterUtils ipfsCluster = new IPFSClusterUtils();
            Resp ipfsRet = ipfsCluster.IPFSUpload(uploadFile.getAbsolutePath());
            if (ipfsRet.getCode() == 0) {    //上传成功
                HashMap<String, Object> jsonRetInfo = JsonUtil.jsonToMap(ipfsRet.getData().toString());
                repoFile.setIpfsHash(jsonRetInfo.get("Hash").toString());
                //System.out.println("文件哈希为："+jsonRetInfo.get("Hash").toString());
                //System.out.println("文件大小为："+jsonRetInfo.get("Size").toString());
                log.info("上传到全量存储成功：" + repoFile.getFileName());
                //信息存入数据库
                repoFile.setIpfsState(1);
                int insertResultId = this.insertRepoFile(repoFile);
                if (insertResultId > 0) {
                    ipfsSuccess = true;
                    log.info("全量存储原始文件信息记录成功！");
                } else {
                    ipfsSuccess = false;
                    log.info("全量存储原始文件信息记录失败！");
                }
                repoFile.setId(insertResultId);
                boolean upChainRet = upChianAndUpdateTx(repoFile, contractAddress);
                if (upChainRet) {
                    chainSuccess = true;
                    log.info(repoFile.getFileName() + "已执行上链操作！");
                }else{
                    chainSuccess = false;
                }
            }
        }else{
            log.error("该文件不存在或者是目录！");
        }
        if(ipfsSuccess&&chainSuccess){
            return Resp.success("文件上传ipfs成功，信息上链成功！",repoFile.getId()); //上传成功返回id
        }else if(ipfsSuccess&&!chainSuccess){
            return Resp.error("文件上传ipfs成功，信息上链失败！");
        }else if(!ipfsSuccess&&chainSuccess){
            return Resp.error("文件上传ipfs失败，信息上链成功！");
        }else{
            return Resp.warning("未知异常！");
        }
    }

    @Override
    public int updateFileInfo(RepoFile repoFile) {
        return fileMapper.updateFileInfo(repoFile);
    }


    /**
     * 文件信息上链并更新数据库的链状态
     * @param repoFile
     * @param contractAddress
     * @return
     */
    public boolean upChianAndUpdateTx(RepoFile repoFile, String contractAddress) {
        TransactionResponse transactionResponse = null;
        try {
            List<Object> paramList = new ArrayList<Object>();
            paramList.add(repoFile.getIpfsHash());
            paramList.add(JSON.toJSONString(repoFile));
            transactionResponse = fiscoUtil.execFiatShamirContract(AppConfigSchedule.upChainKey,paramList,contractAddress,"RepoFile", "set");
        } catch (Exception e) {
            log.error("上链失败，文件名为："+ repoFile.getFileName());
            return false;
        }
        TransactionReceipt transactionReceipt = transactionResponse.getTransactionReceipt();
        String txHash = transactionReceipt.getTransactionHash();

        String blockNumberStr = transactionReceipt.getBlockNumber().substring(2);
        int blockNumberInteger = Integer.parseInt(blockNumberStr, 16);
        BigInteger blockNumber = new BigInteger("" + blockNumberInteger);
        //上链信息保存
        repoFile.setChainHash(txHash);
        repoFile.setChainState(1);
        repoFile.setChainHeight(blockNumber.toString());
        System.out.println("区块高度："+blockNumber.toString()+"，交易哈希:"+txHash);
        this.updateChainHash(repoFile);
        return true;
    }
}
