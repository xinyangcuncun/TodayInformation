package com.news.today.todayinformation.base.tools;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by anson on 2019-12-08.
 */
public class FileUtils {

    /**
     * 从assets 获取文本字符串
     * @param context
     * @param path
     * @return
     */
    public static String getAssetsContent(Context context,String path) {
        InputStream inputStream = context.getClass().getResourceAsStream("/assets/" + path);
        try {
            //将json数据变成字符串
            StringBuilder stringBuilder = new StringBuilder();
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
                //通过管理器打开文件并读取
                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line).append("\r\n");
                }
                bf.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
