package com.cn.test;

import java.io.*;


public class FileHelper {
    public static String readFile(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String ans = "", line = null;
        while((line = reader.readLine()) != null){
            ans += line + "\r\n";
        }
        reader.close();
        return ans;
    }
    public static void writeFile(String content, String filename) throws Exception {
        BufferedWriter writer  = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public static void listFiles(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if(files == null)
            return;
        for(File f : files) {
            if(f.isFile()) {
                System.out.println(f.toString());
            } else if(f.isDirectory()) {
                System.out.println(f.toString());
                listFiles(f.toString());
            }
        }
    }
    public static boolean hasWords(String file, String words) {
        try {
            String s = readFile(file);
            int w_len = words.length();
            int len = s.length();
            for(int i=0;i+w_len<=len;i++) {
                if(s.substring(i, i+w_len).equals(words))
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void findFilesContainsWords(String path, String words) throws Exception {
        File file = new File(path);
        File[] files = file.listFiles();
        if(files == null) return;
        for(File f : files) {
            if(f.isFile()) {
                String s = f.toString();
                int s_len = s.length();
                if(s.substring(s_len-2, s_len).equals(".h") == false) continue; // add filter
                if(hasWords(f.toString(), words))
                    System.out.println(f.toString());
            } else if(f.isDirectory()) {
                findFilesContainsWords(f.toString(), words);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //String ans = readFile("D:\\input.txt");
        //System.out.println(ans);
        //writeFile(ans, "D:\\output.txt");
        //findFilesContainsWords("D:\\clamav-0.98.6", "scanmanager");//在IDE中找
        if(args.length != 1) {
            System.out.println("Usage : \"D:\\clamav-0.98.6\" words");
            return;
        }
        findFilesContainsWords("D:\\clamav-0.98.6", args[0]);//在命令行中找
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
}
