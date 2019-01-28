package com.anson.abc.http;

import com.anson.abc.http.okhttp.OkHttpScheduler;
import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.request.call.ICall;
import com.anson.abc.http.result.IResult;

import java.util.Map;

/**
 * Created by anson on 2019/1/5.
 */
public class HttpHelper {

    private volatile static HttpScheduler httpScheduler;

    public static HttpScheduler getHttpScheduler() {
        if (httpScheduler == null) {
            synchronized (HttpHelper.class) {
                if (httpScheduler == null) {
                    httpScheduler = new OkHttpScheduler();
                }
            }
        }
        return httpScheduler;
    }

    // REMAKE: 待重构 2019/1/5
    protected static  <T> IResult<T> execute(IRequest request, Map<String,Object> params) {
        request.setParams(params);
        ICall call = getHttpScheduler().newCall(request);
        return getHttpScheduler().execute(call);
    }
}
