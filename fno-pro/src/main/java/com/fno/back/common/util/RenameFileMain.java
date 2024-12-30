package com.fno.back.common.util;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/***
 * @des
 * @author Ly
 * @date 2022/12/16
 */

public class RenameFileMain {




    public static void main(String[] args) {
        File file = new File("C:/Users/pansoft/Desktop/测试修改文件名/");
        File[] files = file.listFiles();//获取所有文件和文件夹列表
        String[] names = file.list();//获取所有文件名列表

        for(File a : files){
            if(a.isDirectory()){
                for(File b:a.listFiles()){
                    String name = FileUtil.getName(b);
                    //2019-01-01 105209.jpg
                    //IMG_20190101_105422.jpg
                    String newName = "IMG_"+name.replace("-","").replace(" ","_");
                    FileUtil.rename(b,newName,true);
                }
            }

        }
    }



}
