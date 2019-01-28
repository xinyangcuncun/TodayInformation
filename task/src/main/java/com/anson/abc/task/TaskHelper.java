package com.anson.abc.task;

/**
 * Created by anson on 2019/1/13.
 */
public class TaskHelper {

    public static void submitTask(ITaskBackground iTaskBackground,ITaskCallback iTaskCallback) {

        AsyncTaskInstance instanse = AsyncTaskInstance.getInstanse(iTaskBackground, iTaskCallback);
        //构建线程池管理器
        TaskScheduler.getInstance().submit(instanse);
    }
}
