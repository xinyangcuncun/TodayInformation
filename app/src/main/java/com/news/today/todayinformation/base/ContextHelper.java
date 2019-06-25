package com.news.today.todayinformation.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;



/**
 * Created by anson on 2018/4/6.
 */

public class ContextHelper {

    private static Application application;

    public ContextHelper() {
    }

    public static void init(Application absApplication) {
        if(application == null) {
            application = absApplication;
        }

    }

    public static Context getAppContext() {
        return application.getApplicationContext();
    }

    public static Application getApp() {
        return application;
    }

    public static Resources getResources() {
        Context context = getAppContext();
        return context != null?context.getResources():null;
    }

    public static String getString(int stringId) {
        return getAppContext().getString(stringId);
    }

    public static String getString(int stringId, Object... formatArgs) {
        return getAppContext().getString(stringId, formatArgs);
    }

    public static int getDimensionPixelSize(int dimenId) {
        return getResources().getDimensionPixelSize(dimenId);
    }

    public static int getColor(int color) {
        return ContextCompat.getColor(getAppContext(), color);
    }
}
