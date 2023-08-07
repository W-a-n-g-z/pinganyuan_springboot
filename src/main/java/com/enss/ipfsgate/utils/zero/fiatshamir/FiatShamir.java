package com.enss.ipfsgate.utils.zero.fiatshamir;

import com.enss.ipfsgate.utils.HashUtil;

import java.math.BigInteger;

public class FiatShamir {

    //公开参数，一个素数n,一个模n群的生成元g
    private static int n=8269;
    private static int g=11;
    private static String password="123456";

    public static void main(String[] args) {
        System.out.println("证明者：Prover");
        System.out.println("验证者：Verifier");
        System.out.println();

        int v = (int)(Math.random()*n);
        if(0==v){
            v++;
        }
        int c = (int)(Math.random()*n);
        if(0==c){
            c++;
        }
        System.out.println("原始信息:"+password);
        String pwdSha256 = HashUtil.getStrSha256(password);
        System.out.println("经SHA256加密后的信息:"+pwdSha256);
        //截取SHA256密码前8位转为int整型
        BigInteger bigInteger = new BigInteger(pwdSha256.substring(0,8),16);
        String bigIntegerStr = bigInteger.toString();
        Long x = Long.parseLong(bigIntegerStr) % n;     //这里有可能超出int范围，因此采用long
        System.out.println("截取SHA256信息的前8位，转为int整型，模n后作为摘要计算信息x="+x);
        System.out.println();

        int y=(int) (Math.pow((double)g,(double)x)%n);
        int t=(int) (Math.pow((double)g,(double)v)%n);

        //x = new Long("100131230");
        Long r = (v - c * x) % (n-1);

        int Result = ( (int) (Math.pow((double)g,(double)r)%n) * (int) (Math.pow((double)y,(double)c)%n))  % n;

        System.out.println("======阶段 1: 生成公开参数============");
        System.out.println("质数n="+n);
        System.out.println("模n群的生成元g="+g);

        System.out.println("======阶段 2: 证明者发送y给验证者,验证者存储y和证明者的关联关系==================");
        System.out.println("其中，y = g^x mod n= "+y);

        System.out.println("======阶段 3: 证明者想要进行身份验证, 需要向验证者发送t==================");
        System.out.println("生成随机数v="+v);
        System.out.println("t = g^v mod n = "+t);

        System.out.println("======阶段 4: 验证者选择c作为随机数 ,发送给证明者进行挑战==================");
        System.out.println("c = "+c);

        System.out.println("======阶段 5: 证明者收到c并计算 r=v-cx, 将r发送给验证者==================");
        System.out.println("r = v-cx = "+r);

        System.out.println("======阶段 6: 验证者计算 val是否等于t? ==================");
        System.out.println("t = g^v mod n = "+t);
        System.out.println("val = (( g^r ) * ( y^c )) = "+Result);
        if (t==Result){
            System.out.println("结果：t=val ，Prover已证明他知道密码！");
        } else {
            System.out.println("结果：t！=val，Prover未证明他知道密码！");
        }


    }

}
