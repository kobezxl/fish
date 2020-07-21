package com.cn.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2020-06-29.
 */
public class BeforeBreakFile {
    public static void main(String args[]) {
        try {
            FileReader read = new FileReader("C:/Users/Administrator/Desktop/日志-备份/error.log.2020-07-06-上午");
            BufferedReader br = new BufferedReader(read);
            String row;

            int rownum = 1;
            while ((row = br.readLine()) != null) {
                rownum ++;
            }
            System.out.println("rownum="+rownum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
