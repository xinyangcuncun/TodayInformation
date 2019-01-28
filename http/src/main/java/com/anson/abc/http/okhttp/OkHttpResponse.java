package com.anson.abc.http.okhttp;

import com.anson.abc.http.response.IResponse;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by anson on 2019/1/26.
 */
public class OkHttpResponse implements IResponse{

    private final Response response;

    public OkHttpResponse(Response response) {
        this.response = response;
    }

    @Override
    public String getBodyStr() {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
