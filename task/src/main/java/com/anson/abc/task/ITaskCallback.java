package com.anson.abc.task;

/**
 * Created by anson on 2019/1/13.
 */
public interface ITaskCallback {

    void onSuccess(Object o);

    void onException(Throwable throwable);
}
