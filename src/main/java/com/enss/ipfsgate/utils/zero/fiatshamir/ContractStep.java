package com.enss.ipfsgate.utils.zero.fiatshamir;

import com.enss.ipfsgate.utils.HashUtil;

import java.security.NoSuchAlgorithmException;

public class ContractStep {

    public static void ContractStep1(String password){
        int n=8269;
        int g=11;
        System.out.println("Password:"+password);
        String pwdSha256 = HashUtil.getStrSha256(password);
        int x = Integer.parseInt(pwdSha256.substring(pwdSha256.length()-8)) % n;
        System.out.println("Password hash(x):"+x+" (last 8 bits)");

//        print('\n======Phase 1: Peggy sends y to Victor,Victor store y as Peggy\' token==================')
//        y= pow(g,x,n)
//        print('y= g^x mod P=\t\t',y)
    }

    public static void main(String[] args){
        ContractStep.ContractStep1("123456789");
    }

}
