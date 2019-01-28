package com.anson.abc.http.result;

/**
 * Created by anson on 2019/1/27.
 */
public interface IResultCallBack<T> {

    void onSuccess(IResult<T> t) ;

    void onFailed(IResult t);
}
