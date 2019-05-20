package com.news.today.todayinformation.base;

import android.app.Application;

import com.news.today.todayinformation.splash.SplashActivity;
import com.squareup.leakcanary.LeakCanary;

/**
 * author : renchangcun
 * date : 2019/5/20 14:38
 * description :
 */
public class Test extends Application {

    private static SplashActivity haha;

    public static void save(SplashActivity splashActivity) {
        haha = splashActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
             return;
            }
        LeakCanary.install(this);
    }
}
