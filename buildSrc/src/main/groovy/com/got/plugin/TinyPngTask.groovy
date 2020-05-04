package com.got.plugin

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
        info.resourcesSrc
    }
}