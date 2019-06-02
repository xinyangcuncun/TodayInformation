package com.news.today.todayinformation.splash;

import android.content.Context;

/**
 * Created by anson on 2019/5/19.
 */
public class MemoryTest {


    private static MemoryTest mInstance;

    private final Context context;

    public MemoryTest(Context context) {
        this.context = context;
    }

    public static MemoryTest getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MemoryTest(context);
        }
        return mInstance;
    }
}
