package com.enss.ipfsgate.utils.zero.fiatshamir;

import com.enss.ipfsgate.utils.HashUtil;
import com.enss.ipfsgate.utils.Resp;

import java.math.BigInteger;

public class FiatShamirUtil {

    //公开参数，一个素数n,一个模n群的生成元g
    private static int n=8269;
    private static int g=11;

    /**
     * 生成摘要计算信息x
     * @param keyword 原始信息
     * @return 摘要计算信息x
     */
    public static Long contractStep0(String keyword){
        System.out.println("原始信息:"+keyword);
        String pwdSha256 = HashUtil.getStrSha256(keyword);
        System.out.println("经SHA256加密后的信息:"+pwdSha256);
        //截取SHA256密码前8位转为int整型
        BigInteger bigInteger = new BigInteger(pwdSha256.substring(0,8),16);
        String bigIntegerStr = bigInteger.toString();
        Long x = Long.parseLong(bigIntegerStr) % n;
        System.out.println("截取SHA256信息的前8位，转为int整型，模n后作为摘要计算信息x="+x);
        System.out.println("======阶段 0: 生成公开参数============");
        System.out.println("质数n="+n);
        System.out.println("模n群的生成元g="+g);
        return x;
    }

    /**
     * 在合约上注册加密后的验证信息
     * @param x 摘要计算信息x
     * @return 需要由智能合约存储的加密信息
     */
    public static Integer contractStep1(Long x){
        int y = getPowMod(g,Integer.parseInt(x.toString()),n);
        System.out.println("======阶段 1: 证明者发送加密信息y给验证者,验证者存储y和证明者的关联关系==================");
        System.out.println("y= g^x mod n = "+y);
        return y;
    }

    /**
     * 发起验证
     * @return 由随机数v生成的申请验证信息t
     */
    public static Integer contractStep2(Integer v){
        System.out.println("======阶段 2: 证明者想要进行身份验证, 需要向验证者发送t==================");
        System.out.println("生成随机数v="+v);
        int t=getPowMod(g,v,n);
        System.out.println("t = g^v mod n = "+t);
        return t;
    }

    public static Integer getRandomWithN(){
        int v = (int)(Math.random()*n);
        if(0==v){
            v++;
        }
        return v;
    }

    /**
     * 接收验证者发送的随机数c，计算挑战信息r
     * @param x
     * @param c
     * @param v
     * @return
     */
    public static Long contractStep45(Long x,Integer c,Integer v){
        System.out.println("======阶段 4: 接受由验证者发送的随机数c，用于挑战==================");
        System.out.println("======阶段 5: 证明者计算 r=v-cx, 将r发送给验证者==================");
        Long r = (v - c * x) % (n-1);
        if(r<0){
            r = r +n-1;
        }
        System.out.println("r = v-cx ="+r);
        return r;
    }


    /***
     * 计算大数值的平方后的余数
     * 将num1的num2次方，求余num3
     */
    public static int getPowMod(int num1,int num2,int num3){
        BigInteger n1 = new BigInteger(num1+"");
        BigInteger n2 = new BigInteger(num2+"");
        BigInteger n3 = new BigInteger(num3+"");

        BigInteger y = n1.pow(num2).mod(n3);

        return Integer.parseInt(y.toString());
    }

}
