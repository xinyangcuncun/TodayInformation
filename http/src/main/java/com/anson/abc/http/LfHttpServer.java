package com.anson.abc.http;

import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.result.IResult;

import java.util.Map;

/**
 * Created by anson on 2019/1/5.
 */
public class LfHttpServer {

    protected <T> IResult<T> execute(IRequest request, Map<String,Object> params) {
        return HttpHelper.execute(request,params);
    }
}
