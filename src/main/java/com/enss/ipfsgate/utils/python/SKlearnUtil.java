package com.enss.ipfsgate.utils.python;
import org.python.util.PythonInterpreter;
import org.python.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 机器学习 for python 的实体类
 */
public class SKlearnUtil {

    public static void main(String[] args) {
        SKlearnUtil.runtimeLearning();
    }

    public static void runtimeLearning() {
        Process proc = null;
        try {
            for (int i=0;i<1000;i++){
                proc = Runtime.getRuntime().exec("python E:\\工作\\工作2022\\工作2022-08\\11.隐私计算包2.0\\模型使用2.0\\model_test.py");// 执行py文件

            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(i+":"+line);
            }

            in.close();
            proc.waitFor();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void learning() {
        //选择执行的的Python语句
        PythonInterpreter interp = new PythonInterpreter();
        System.out.println("hello, zeek");

        interp.exec("import numpy as np");
        interp.exec("import pandas as pd");
        interp.exec("import sklearn from sklearn.linear_model import LogisticRegression");
        interp.exec("import joblib");

        //PyObject x = interp.get("x");
        System.out.println("Goodbye, cruel world");
    }



    public static void pythonTest() {
        //首先调用python的解释器
        PythonInterpreter interpreter = new PythonInterpreter();
        //选择执行的的Python语句
        PythonInterpreter interp =
                new PythonInterpreter();
        System.out.println("Hello, brave new world");
        interp.exec("import sys");
        interp.exec("print sys");
        interp.set("a", new PyInteger(42));
        interp.exec("print a");
        interp.exec("x = 2+2");
        PyObject x = interp.get("x");
        System.out.println("x: "+x);
        System.out.println("Goodbye, cruel world");
    }

}
