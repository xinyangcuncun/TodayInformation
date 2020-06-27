package com.news.today.todayinformation.main.tools;

import androidx.annotation.IntDef;

import static com.news.today.todayinformation.main.tools.MainConstantTool.BEIJING;
import static com.news.today.todayinformation.main.tools.MainConstantTool.HANGZHOU;
import static com.news.today.todayinformation.main.tools.MainConstantTool.SHANGHAI;
import static com.news.today.todayinformation.main.tools.MainConstantTool.SHENZHEN;

/**
 * Created by anson on 2018/11/18.
 */
@IntDef({SHANGHAI,HANGZHOU,BEIJING,SHENZHEN})
public @interface MainConstantTool {
    int SHANGHAI = 0;
    int HANGZHOU = 1;
    int BEIJING = 2;
    int SHENZHEN = 3;
}
