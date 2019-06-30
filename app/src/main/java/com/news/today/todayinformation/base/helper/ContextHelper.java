package com.news.today.todayinformation.base.helper;

import android.app.Application;
import android.content.Context;

/**
 * Created by anson on 2019/6/30.
 */
public class ContextHelper {


    private static ContextHelper mInstance;

    private Application mApplication;

    public static ContextHelper getInstance() {
        if (mInstance == null) {
            mInstance = new ContextHelper();
        }
        return mInstance;
    }

    public void init(Application application) {
        this.mApplication = application;
    }

    public Context getApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
