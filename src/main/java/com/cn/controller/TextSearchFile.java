package com.cn.controller;

import com.cn.test.FileHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020-07-03.
 */

public class TextSearchFile {
    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<File>();
        if (folder.isFile())
            result.add(folder);
        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
//                if (file.getName().toLowerCase().contains(keyword)) {
                try {
                    if(FileHelper.findStringInFile(file.getAbsolutePath(),keyword)){
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ;

//                }
                return false;
            }
        });
        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
    }

    public static boolean findStringInFile(String path,String keyWord) throws IOException {
        boolean flag = false;
        File file = new File(path);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if(line.startsWith("#")){
                continue;
            }
            //指定字符串判断处
            if (line.contains(keyWord)) {
                flag=true;
            }
        }
        return  flag;
    }
    public static void main(String[] args) {
        List<File> files = searchFiles(new File("D:/text1"), "ZYJ000000583479");
        System.out.println("共找到:" + files.size() + "个文件");
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }
}

