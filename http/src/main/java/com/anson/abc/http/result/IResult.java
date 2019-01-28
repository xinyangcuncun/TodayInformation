package com.anson.abc.http.result;

/**
 * Created by anson on 2019/1/26.  所有 IResponse 解析后的结果
 */
public interface IResult<T> {

    boolean isSuccess();

    int getCode();

    T data();
}
