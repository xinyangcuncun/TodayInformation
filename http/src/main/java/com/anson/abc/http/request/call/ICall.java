package com.anson.abc.http.request.call;

import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.response.IResponse;

/**
 * Created by anson on 2019/1/6.
 */
public interface ICall {

    IResponse execute();

    IRequest getRequest();
}
