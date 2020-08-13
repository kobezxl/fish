package com.cn.controller;

import java.io.*;

/**
 * Created by Administrator on 2020-06-29.
 */
public class BreakFile {
    public static void main(String args[]) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:/Users/Administrator/Desktop/日志-备份/error.log"), "GBK"));
            String row;

            int rownum = 1;

            int fileNo = 1;
            FileWriter fw = new FileWriter("D:/text1/"+fileNo +".txt");
            File file = new File("D:/text1/"+fileNo +".txt");
            while ((row = br.readLine()) != null) {
                rownum ++;
                fw.append(row + "\r\n");

                if((rownum / 3282) > (fileNo - 1)){
                    fw.close();
                    fileNo ++ ;
                    fw = new FileWriter("D:/text1/"+fileNo +".txt");
                }
            }
            fw.close();
            System.out.println("rownum="+rownum+";fileNo="+fileNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
