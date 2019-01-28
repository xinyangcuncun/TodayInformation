package com.anson.abc.http;

import com.anson.abc.http.parser.IParser;
import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.request.call.ICall;
import com.anson.abc.http.response.IResponse;
import com.anson.abc.http.result.IResult;

/**
 * Created by anson on 2019/1/6.
 */
public abstract class HttpScheduler {

    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call) {

        //IRespnse 和  IResult 进行一个转换
        IResponse iResponse = call.execute();
        IRequest request = call.getRequest();
        IParser parser = request.getParser();
        return parser.parseResponse(request,iResponse);
    }

}
