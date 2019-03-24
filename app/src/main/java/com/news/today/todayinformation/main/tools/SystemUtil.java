package com.news.today.todayinformation.main.tools;

import android.content.Context;
import android.util.TypedValue;

public class SystemUtil {
    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     *
     */
    public static int sp2px(Context context, float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,context.getResources().getDisplayMetrics());
    }

}
