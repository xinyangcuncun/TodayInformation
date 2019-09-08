package com.web.god.ipc.result;

/**
 * Created by anson on 2019/8/29.
 */
public interface IResult {

    boolean isSuccess();

    int getCode();

    String data();
}
