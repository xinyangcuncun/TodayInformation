package com.news.today.todayinformation.base;

import android.app.Application;

import com.news.today.todayinformation.base.crash.CrashProtectManager;

/**
 * author : renchangcun
 * date : 2019/6/13 20:07
 * description :
 */
public class TodayInformationApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashProtectManager.getInstance().install(this);
    }
}
