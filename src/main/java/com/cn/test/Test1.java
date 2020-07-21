package com.cn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2020-05-19.
 */
public class Test1 {


    public static void main(String[] args) {
        String url = "http://www.poker-dictionary.net/wp-login.php";
        String param = "log=zxl&pwd=123456";
        String response = httpUrlConnection(url,param);
        System.out.println(response);
    }


    /* 请求PHP接口
* @param pathUrl 接口地址
* @param params 请求参数
* @return httpUrlConnection
*/
    public  static String httpUrlConnection(String pathUrl, String params) {
        try {
            // 建立连接
            URL url = new URL(pathUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();


            // //设置连接属性
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(false);// 忽略缓存
            httpConn.setRequestMethod("POST");// 设置URL请求方法
            String requestString = "param="+params;

            // 设置请求属性
            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
            byte[] requestStringBytes = requestString.getBytes("UTF-8");
            httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");
            //
            // 建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(requestStringBytes);
            outputStream.close();
            // 获得响应状态
            int responseCode = httpConn.getResponseCode();


            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
                // 当正确响应时处理数据
                StringBuffer sb = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                // 处理响应流，必须与服务器响应流输出的编码一致
                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
                System.out.println(sb.toString());
                return sb.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
