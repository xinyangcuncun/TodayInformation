package com.anson.abc.http.okhttp;

import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.request.call.ICall;
import com.anson.abc.http.response.IResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by anson on 2019/1/6.
 */
public class OkHttpCall implements ICall{

    private  IRequest request;
    private  Call call;

    public OkHttpCall(IRequest request, Call call) {
        this.call = call;
        this.request = request;
    }

    @Override
    public IResponse execute() {
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpResponse okHttpResponse = new OkHttpResponse(response);
        return okHttpResponse;
    }

    @Override
    public IRequest getRequest() {
        return request;
    }
}
