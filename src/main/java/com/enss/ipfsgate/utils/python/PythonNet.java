package com.enss.ipfsgate.utils.python;

import com.enss.ipfsgate.config.AppConfigSchedule;

import java.lang.System;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.InputStream;

public class PythonNet {

    public static float pythonNetExec(int cmd, String content){
        // TODO Auto-generated method stub
        Socket socket = null;
        String ret = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String host=addr.getHostName();
            //String ip=addr.getHostAddress().toString(); //获取本机ip
            //log.info("调用远程接口:host=>"+ip+",port=>"+12345);
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            //socket = new Socket(host,12345);
            socket = new Socket(AppConfigSchedule.yinsiServerIp, AppConfigSchedule.yinsiServerPort);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            if(1==cmd){     //常规分析结论
                out.print("cmd1");
                // 发送内容
                out.print(content);
                // 告诉服务进程，内容发送完毕，可以开始处理
                out.print("over");
            }else if(2==cmd){   //更改模型
                out.print("cmd2");
                // 发送内容
                out.print(content);
                // 告诉服务进程，内容发送完毕，可以开始处理
                out.print("over");
            }
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            ret = sb.toString().trim();
            // 解析结果
            //JSONArray res = JSON.parseArray(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {if(socket!=null) socket.close();} catch (IOException e) {}
        }
        float yinsiResult = -1;
        if("500".equals(ret)||"500"==ret){
            return -1;
        }else if(cmd==1 && ret.contains(",")){    //计算正常，返回类似1.0000,0.0000的结果，其中前者是有问题的概率
            ret = ret.split(",")[1];
            yinsiResult = Float.parseFloat(ret);
        }else if(cmd == 2){ //0失败，1成功
            if(ret=="success" || ret.equals("success")){
                yinsiResult = 1;
            }else{
                System.out.println("隐私模型启动失败，失败信息："+ret);
                yinsiResult = -2;
            }
        }
        return yinsiResult;
    }

    public static void main(String[] args){

        String content = "0,1,22,10,54540,8314,0,0,0,4,4,0,0,0,0,1,0,0,200,200,1,0,0,0,0,0,0.05,0.05";
        for (int i=0;i<10;i++){
            System.out.print(i+":");
            PythonNet.pythonNetExecTest(1,content);
        }
    }

    public static float pythonNetExecTest(int cmd, String content){
        // TODO Auto-generated method stub
        Socket socket = null;
        String ret = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String host=addr.getHostName();
            //String ip=addr.getHostAddress().toString(); //获取本机ip
            //log.info("调用远程接口:host=>"+ip+",port=>"+12345);
            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            //socket = new Socket(host,12345);
            socket = new Socket("192.168.1.152", 12345);
            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            if(1==cmd){     //常规分析结论
                out.print("cmd1");
                // 发送内容
                out.print(content);
                // 告诉服务进程，内容发送完毕，可以开始处理
                out.print("over");
            }else if(2==cmd){   //更改模型
                out.print("cmd2");
                // 发送内容
                out.print(content);
                // 告诉服务进程，内容发送完毕，可以开始处理
                out.print("over");
            }
            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
            String tmp = null;
            StringBuilder sb = new StringBuilder();
            // 读取内容
            while((tmp=br.readLine())!=null)
                sb.append(tmp).append('\n');
            ret = sb.toString().trim();
            // 解析结果
            //JSONArray res = JSON.parseArray(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {if(socket!=null) socket.close();} catch (IOException e) {}
        }
        float yinsiResult = -1;
        if("500".equals(ret)||"500"==ret){
            return -1;
        }else if(cmd==1 && ret.contains(",")){    //计算正常，返回类似1.0000,0.0000的结果，其中前者是有问题的概率
            ret = ret.split(",")[1];
            yinsiResult = Float.parseFloat(ret);
        }else if(cmd == 2){ //0失败，1成功
            if(ret=="success" || ret.equals("success")){
                yinsiResult = 1;
            }else{
                System.out.println("隐私模型启动失败，失败信息："+ret);
                yinsiResult = -2;
            }
        }
        return yinsiResult;
    }



}

