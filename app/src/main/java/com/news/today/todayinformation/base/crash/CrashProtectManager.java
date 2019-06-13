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
 * author : renchangcun
 * date : 2019/6/13 17:26
 * description :
 */

public class CrashProtectManager {

    private static CrashProtectManager INSTANCE;
    private Context mContext;

    private CrashProtectManager() {

    }

    public static CrashProtectManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashProtectManager();
        }
        return INSTANCE;
    }

    public void install(Context ctx) {
        this.mContext = ctx;
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //写日志
                handleThrowable(e);
                if (t == Looper.getMainLooper().getThread()) {
                    safeMode();
                }
            }
        });

    }

    private void handleThrowable(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();

        try {
            // 用于格式化日期,作为日志文件名的一部分
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + ".txt";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = mContext.getCacheDir() ;
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir.getAbsolutePath(),fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(result.getBytes());
                fos.close();
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
    }


    private void safeMode() {

        while (true) {
            try {
                Looper.loop();
            } catch (Throwable e) {
                handleThrowable(e);
            }
        }
    }

}
