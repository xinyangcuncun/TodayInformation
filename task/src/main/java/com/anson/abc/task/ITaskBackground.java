package com.anson.abc.task;

/**
 * Created by anson on 2019/1/13.
 */
public interface ITaskBackground<Result> {

    Result onBackground();
}
