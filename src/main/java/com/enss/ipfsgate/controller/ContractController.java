//发布合约
package com.enss.ipfsgate.controller;

import com.alibaba.fastjson.JSON;
import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.model.config.Contract;
import com.enss.ipfsgate.model.threat.FullLogInfo;
import com.enss.ipfsgate.service.ContractService;
import com.enss.ipfsgate.service.FullLogInfoService;
import com.enss.ipfsgate.utils.Resp;
import com.enss.ipfsgate.utils.fisco.web3j.FiscoUtil;
import com.enss.ipfsgate.utils.zero.fiatshamir.FiatShamirUtil;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {

    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private ContractService contractService;

    @Autowired
    private FiscoUtil fiscoUtil;

    /**
     * 发布智能合约，1验证用户零知识
     */
    @RequestMapping(value = "deployContractBase")
    public Resp deployFullLogBase(@RequestParam Integer contractType,@RequestParam String contractName){
        TransactionResponse deployResult= null;
        System.out.println("contractType:"+contractType);
        System.out.println("contractName:"+contractName);
        if(1==contractType){
            deployResult = fiscoUtil.deployContract("FiatShamir");
        }else{
            return Resp.error("不支持的合约类型");
        }
        if(null==deployResult){
            return Resp.error("合约发布失败");
        }

        String contractAddress = deployResult.getContractAddress();
        Contract contract = new Contract();
        contract.setContractType(contractType);
        contract.setContractName(contractName); //这个合约名是对合约的版本+描述信息
        contract.setContractAddress(contractAddress);
        int addResult = contractService.insertContract(contract);
        if(addResult==1){
            return Resp.success("合约地址："+contractAddress);
        }else{
            return Resp.error("合约地址入库失败！");
        }
    }

    /**
     * 测试通过零知识证明合约验证身份
     * @param keyword 注册信息
     * @param cKeyword 发起验证信息
     * @return
     */
    @RequestMapping(value = "proveIdentity")
    public Resp proveIdentity(@RequestParam String keyword,@RequestParam String cKeyword){
        String privateKey = "b1b0c178f581e776cbb5fd0afc4d997c47ff35e2bb0ff6275abddfa69f25a767";
        String contractAddress = contractService.selectContractAddress(1);

        //1.注册验证信息
        Long x1 = FiatShamirUtil.contractStep0(keyword);
        //2.发起验证申请
        int y = FiatShamirUtil.contractStep1(x1);
        List<Object> paramList1 = new ArrayList<>();
        paramList1.add(y);
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(privateKey,paramList1,contractAddress,"FiatShamir","Step1_register");
            String txJsonStr = JSON.toJSONString(tr);
            System.out.println("Step1_register成功，返回值为："+txJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.计算并发送挑战信息
        int v = FiatShamirUtil.getRandomWithN();
        int t = FiatShamirUtil.contractStep2(v);
        List<Object> paramList2 = new ArrayList<>();
        paramList2.add(t);
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(privateKey,paramList2,contractAddress,"FiatShamir","Step2_login");
            String txJsonStr = JSON.toJSONString(tr);
            System.out.println("Step2_login成功，返回值为："+txJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //4.接收链返回的随机数c
        int c=0;
        List<Object> paramList3 = new ArrayList<>();
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(privateKey,paramList3,contractAddress,"FiatShamir","Step3_randomchallenge");
            c = Integer.parseInt(tr.getValues().replace("[","").replace("]",""));
            System.out.println("Step3_randomchallenge成功，返回值为："+c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //5.发送挑战信息r
        Long x2 = FiatShamirUtil.contractStep0(cKeyword);
        Long r = FiatShamirUtil.contractStep45(x2,c,v);
        System.out.println("r:"+r);
        List<Object> paramList4 = new ArrayList<>();
        paramList4.add(r);
        String result = "";
        try {
            TransactionResponse tr = fiscoUtil.execFiatShamirContract(privateKey,paramList4,contractAddress,"FiatShamir","Step45_verify");
            result = JSON.toJSONString(tr);
            System.out.println("Step45_verify，返回值为："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Resp.success(result);
    }

}
