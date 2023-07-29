package com.enss.ipfsgate.utils.fisco.web3j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.enss.ipfsgate.config.AppConfigSchedule;
import com.enss.ipfsgate.utils.network.OkHttp;
import org.fisco.bcos.sdk.client.protocol.response.BcosBlock;
import org.fisco.bcos.sdk.client.protocol.response.BcosBlockHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RpcUtil {

    /***
     *
     * @param groupId 群组ID
     * @param blockHash 区块哈希
     * @param includeTransactions 包含交易标志(true显示交易详细信息，false仅显示交易的hash)
     * @return
     */
    public static BcosBlock getBlocakByHash(int groupId,String blockHash,boolean includeTransactions){
        List<Object> paramsList = new ArrayList<Object>();
        paramsList.add(groupId);
        paramsList.add(blockHash);
        paramsList.add(includeTransactions);
        Map<String,Object> paraMap = new HashMap<String,Object>();
        paraMap.put("jsonrpc","2.0");
        paraMap.put("method","getBlockByHash");
        paraMap.put("params",paramsList);
        paraMap.put("id","1");
        String paraJson = JSON.toJSONString(paraMap);
        String url = "http://"+ AppConfigSchedule.sdkIp+":"+ AppConfigSchedule.sdkRpcPort;
        String result = OkHttp.doPostString(url,paraJson);
        System.out.println("getBlocakByHash result:"+result);
        return JSON.parseObject(result,BcosBlock.class);
    }

    public static BcosBlockHeader.BlockHeader getBlockByHash(int groupId,String blockHash,boolean includeTransactions){
        List<Object> paramsList = new ArrayList<Object>();
        paramsList.add(groupId);
        paramsList.add(blockHash);
        paramsList.add(includeTransactions);
        Map<String,Object> paraMap = new HashMap<String,Object>();
        paraMap.put("jsonrpc","2.0");
        paraMap.put("method","getBlockByHash");
        paraMap.put("params",paramsList);
        paraMap.put("id","1");
        String paraJson = JSON.toJSONString(paraMap);
        String url = "http://"+ AppConfigSchedule.sdkIp+":"+ AppConfigSchedule.sdkRpcPort;
        String result = OkHttp.doPostString(url,paraJson);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return JSON.parseObject(JSONObject.toJSONString(jsonObject.get("result")), BcosBlockHeader.BlockHeader.class);
        // return JSON.parseObject(result,BcosBlock.class);
    }


    /*
    * getBlocakByHash()结果样例
    *
    * {
    "id": 1,
    "jsonrpc": "2.0",
    "result": {
        "dbHash": "0xb3b72417f218cee3400c8e0d395d8976b6bd5ec98842101ab7e97ee82154ece8",
        "extraData": [],
        "gasLimit": "0x0",
        "gasUsed": "0x0",
        "hash": "0x3081bb9626c9866f371c203e4d5786b7de43a127d6e705755c835aa14e062d7d",
        "logsBloom": "0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
        "number": "0x8",
        "parentHash": "0x08d6f07edec60b45981a9d9cc71f45bf782cab2c1d1e79ddc77ca6c749d3168f",
        "receiptsRoot": "0xbe3c0122ab66bb0cbf31bc9fcac75fc7e2dc0185e7267c077687deaa99069dde",
        "sealer": "0x2",
        "sealerList": [
            "741e73afe35dd774670060eaad116054af124066a45b9f97691ad4ccf61ffe67de047d335cb1ef74d3126a951801c42382a83c1c096c7e2ec08fb3e926da9596",
            "b40753ef770b76abc0f765106b824d4090c8d27f206a1112cd136c23677965c8b11b11530d09c77633bf87d74c5059470c87806e413779cdee054ec04102294a",
            "e502b3d1bf0b6f9195d8f95057c952fce5afe6a82750473660e1f0c1d8adf78420fe31936fcfbac85d4869cb32ce9aa72a776e63d8713c8ebd461e2938548507"
        ],
        "signatureList": [
            {
                "index": "0x0",
                "signature": "0x86fe774657909664e62502c2143afc221065e7dc1d905017b6aff51f34695ab535ef5928cc85c954defe41ef4b033e1584307c58155218825ec329a4830c616001"
            },
            {
                "index": "0x2",
                "signature": "0x7a665bcb3709db1dbd3d68bb484d394f0cd1bf5f36d2eeae818b17992107ebf65cf0195a7ee42ca2b41ab5af76fa11ba096c7b75a5d8edc5370509b26a537dc101"
            },
            {
                "index": "0x1",
                "signature": "0xc366516927c5310a94fc880c0b7900d4f285b6b2ec429a5233bb639f88ca4d962a8b1700d5e16286482c675ecca0116d6d4fc977b1903d3ab6204add9fd3796401"
            }
        ],
        "stateRoot": "0xb3b72417f218cee3400c8e0d395d8976b6bd5ec98842101ab7e97ee82154ece8",
        "timestamp": "0x1804fd82de9",
        "transactions": [
            {
                "blockHash": "0x3081bb9626c9866f371c203e4d5786b7de43a127d6e705755c835aa14e062d7d",
                "blockLimit": "0x1fb",
                "blockNumber": "0x8",
                "chainId": "0x1",
                "extraData": "0x",
                "from": "0x64e00be5865d7f2b7867cba8d97eef6d6989338e",
                "gas": "0x419ce0",
                "gasPrice": "0x51f4d5c00",
                "groupId": "0x1",
                "hash": "0x6cd5d1761bac33f44f77de770462f35819e5b713a51430c0bbb3237f86b13cec",
                "input": "0xe942b51600000000000000000000000000000000000000000000000000000000000000400000000000000000000000000000000000000000000000000000000000000080000000000000000000000000000000000000000000000000000000000000000a746573742068617368320000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000117465737420617574686f72696e666f2032000000000000000000000000000000",
                "nonce": "0x23f72560cd78cbb7122a7e3807331cd808e92cf0b31528e421920bd631c97a9",
                "signature": {
                    "r": "0x7f5324e7fd71a8fcb51d32dccfe83d6f950e81057712afac9d7291fa4b2bcb7f",
                    "s": "0x7d213906fae3c0b1db71c080039dcbdcf49410534f5f2045e20c55c983aea5dd",
                    "signature": "0x7f5324e7fd71a8fcb51d32dccfe83d6f950e81057712afac9d7291fa4b2bcb7f7d213906fae3c0b1db71c080039dcbdcf49410534f5f2045e20c55c983aea5dd01",
                    "v": "0x1"
                },
                "to": "0xdef67204289cc281678e6558d5ca6679861d878d",
                "transactionIndex": "0x0",
                "value": "0x0"
            }
        ],
        "transactionsRoot": "0x009e99ada9a3a1f8158da76fc7539ece6d05f52faa9f751a0a32ccf4e8dbc9e9"
    }
}
    *
    *
    *
    * */


}
