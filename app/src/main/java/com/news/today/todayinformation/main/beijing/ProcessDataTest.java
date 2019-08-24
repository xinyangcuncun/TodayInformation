package com.news.today.todayinformation.main.beijing;

/**
 * Created by anson on 2019/8/18.
 */
public class ProcessDataTest {

    private static ProcessDataTest mInstance;

    private String processDec;

    private ProcessDataTest() {
//        Log.e("ShanghaiDetailActivity","pid = " + android.os.Process.myPid());
    }

    public static synchronized ProcessDataTest getIntance() {
        if (mInstance == null) {
            mInstance = new ProcessDataTest();
        }
        return mInstance;
    }

    public String getProcessDec() {
        return processDec;
    }

    public ProcessDataTest setProcessDec(String processDec) {
        this.processDec = processDec;
        return this;
    }
}
