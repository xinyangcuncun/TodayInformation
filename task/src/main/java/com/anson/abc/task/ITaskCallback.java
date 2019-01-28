package com.anson.abc.task;

/**
 * Created by anson on 2019/1/13.
 */
public interface ITaskCallback<Result> {

    void onComplete(Result o);

    void onException(Throwable throwable);
}
