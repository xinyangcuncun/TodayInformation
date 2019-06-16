package com.news.today.todayinformation.base.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anson on 2019/6/16.
 */
public class CrashProtectManager {

    private static CrashProtectManager mInstance;
    private static Context mContext;

    private CrashProtectManager() {

    }

    public static CrashProtectManager getInstance(Context context) {
        if (mInstance == null) {
            mContext = context.getApplicationContext();
            mInstance = new CrashProtectManager();
        }
        return mInstance;
    }

    public void init() {
        //crach 防护
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                handleFileException(e);
                if (t == Looper.getMainLooper().getThread()) {
                    handleMainThread(e);
                }
            }
        });
    }

    //日志文件系统
    private void handleFileException(Throwable e) {
        //通过Throwable 生成字符串
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.close();
        String result = writer.toString();
        //定义文件名
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String time = dateFormat.format(new Date());
        String fileName = "crash-" + time + ".txt";
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File cacheDir = mContext.getCacheDir();
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();
                }
                File cacheFile = new File(cacheDir.getAbsolutePath(), fileName);
                if (!cacheDir.exists()) {
                    cacheFile.createNewFile();
                }
                //把字符串写入到文件
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                outputStream.write(result.getBytes());
                outputStream.close();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    private void handleMainThread(Throwable e) {
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable e1) {
                handleFileException(e1);
            }
        }
    }
}
