package com.got.plugin

import com.tinify.Tinify
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class TinyPngTask extends DefaultTask{

    def TinyPngInfo  info

    public TinyPngTask() {
        info = project.tinyInfo
    }

    //相当于Task的执行入口
    @TaskAction
    void doAction(){
       //如何来做自动化的图片压缩
        /**
         * 1\集成Tiny-Java库做图片压缩算法
         * 2\这个插件类似一个工具类，有输入和输出
         * 3\使用Tiny—Java Api 做图片压缩
         */
        //课后作业(待完成)
        /**
         * 1、白名单：不想继续压缩的文件
         * 2、压缩进度
         * 3、压缩记录和统计
         */
        ArrayList<String> srcList = info.resourcesSrc
        String apiKey = info.apiKey
        for (int i = 0; i <srcList.size() ; i++) {
            String src = srcList.get(i)
            File fileDir = new File(project.projectDir,src)
            if (fileDir.exists() && fileDir.isDirectory()) {
                //判断文件是否存在及是否是文件夹
                File[] files = fileDir.listFiles()
                for (int j = 0; j < files.size(); j++) {
                    File file = files[i]
                    String filePath = file.getPath()
                    //使用TinyPng的APi
                    Tinify.setKey(apiKey);
                    Tinify.fromFile(filePath).toFile(filePath);
                }
            }
        }
    }
}