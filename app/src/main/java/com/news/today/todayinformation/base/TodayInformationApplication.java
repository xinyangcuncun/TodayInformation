package com.news.today.todayinformation.base;

import android.app.Application;

import com.news.today.todayinformation.base.crash.CrashProtectManager;
import com.news.today.todayinformation.base.helper.ContextHelper;

/**
 * Created by anson on 2019/6/16.
 */
public class TodayInformationApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance(this).init();
        //全局Context获取类
        ContextHelper.getInstance().init(this);
    }
}
