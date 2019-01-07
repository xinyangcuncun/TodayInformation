package com.anson.abc.http;

import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.request.call.ICall;

/**
 * Created by anson on 2019/1/6.
 */
public abstract class HttpScheduler {

    public abstract ICall newCall(IRequest request);

    public Object execute(ICall call) {
        return call.execute();
    }
}
