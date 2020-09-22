package com.shrm.io;

import com.shrm.utils.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wxy e-mail:wxypersonal@163.com
 * @date 创建时间:2017年5月22日 下午3:59:26
 * @version 1.0
 * @company: xxx科技公司
 * @description: InputStreamReader 是字节流向字符流转换的桥梁\ 这个类是按照格式pattern解析
 *               txt格式文件中的数据. ps: 其实读取其他数据格式文件也是类似的, 比如要读取.dat文件.
 * */
public class DatFileReader {
    /**
     * 在这里进行测-
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("E:/wxy-workspace/space01/wxy/resources/222.txt");
        //注意: 这里的file路径自己可以为了测试, 随便变化的, 写一个测试文件全路径即可
        List<List<Object>> arr = parseTxt(file, "\\.");
        System.out.println(arr);
    }


    /**
     * 注意, 此类进行解析, 是按照gbk默认编码进行解析, 因为InputStreamReader的转换编码就是GBK
     * @param file 为解析文件的路径
     * @param pattern 解析格式 注意pattern的输入,是否是转义字符来解析的, 如果是, 注意正确的输入,因为split()方法支持正则表达式
     * @return 数据
     */
    public static List<List<Object>> parseTxt(File file, String pattern) {
        String fileName = file.getName(); // testFile.txt
        if (StringUtils.isEmpty(fileName)) {
            System.out.println("输入参数file错误, 必须精确到文件名.txt后缀全路径才行");
            return null;
        }
        // 获取后缀名 如果后缀名没有 . 则返回""
        String suffix = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1) ;

        List<List<Object>> listRows = null;//用于返回使用
        if ("txt".equals(suffix)) {
            BufferedReader bf = null;
            String temp;
            try {
                //注意:inputStreamReader是一个字节流向字符流转换的桥梁 bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                FileInputStream fis = new FileInputStream(new File(file, ""));

                /*
                 * 注意: 我这里吧GBK编码格式写死了, 具体工作中, 需要看txt文件的编码格式, 如果是windows系统默认, 那就是
                 * GBK, 如果是其他的, 那可以改成utf-8,
                 */
                bf = new BufferedReader(new InputStreamReader(fis, "GBK"));//从这一句可以看出, 这里可以指定InputStreamReader的编码格式.
                listRows = new ArrayList<>();
                while ((temp = bf.readLine()) != null) {
                    if (temp.replaceAll(" ", "").length() > 0) {
                        String[] arr = temp.split(pattern);
                        List<Object> row = new ArrayList<>();
                        for (String string : arr) {//循环遍历上面的数组
                            row.add(string);
                        }
                        listRows.add(row);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("txt文件读取失败");
            }finally{
                //在返回之前关闭流
                if (bf != null) {
                    try {
                        bf.close();
                    } catch (IOException e2) {
                        //添加日志文件吧
                    }
                }
            }
        }else{
            System.out.println("发生异常: 文件的后缀名不是以.txt结尾的");
            return null;
        }
        return listRows;
    }
}