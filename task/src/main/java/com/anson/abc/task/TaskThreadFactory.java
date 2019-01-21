package com.anson.abc.task;

import java.util.concurrent.ThreadFactory;

/**
 * Created by anson on 2019/1/19.
 */
public class TaskThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"task_thread_pool");
    }
}
