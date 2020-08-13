package com.cn.controller;

import java.io.*;
import java.nio.charset.Charset;

public class ReadTest{
            public static void main(String[] args) {
                // 读控制台输入的du文字!
                BufferedReader br = null;
                String str = null;
                System.out.println(Charset.defaultCharset());
                try {
   /*                 br = new BufferedReader(new InputStreamReader(System.in));
                    while (true) {
                        str = br.readLine();
                        if (str.equals("错误====sql===INSERT INTO t_interf_platform_order"))
                            break;
                        System.out.println(str);
                    }*/
                    // 读文本文件..
                    InputStreamReader isr = new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\Desktop\\日志-备份\\error.log.2020-08-12-上午"),"gbk");
                    br =  new BufferedReader(isr);
                    int count = 0;
                    for (str = br.readLine(); str != null; str = br.readLine()) {
                        //打印你读的文本数据!
                        if(str.contains("错误====sql===INSERT INTO t_interf_platform_order")){
                            System.out.println(str);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }