package com.web.god.ipc.response;

import com.web.god.ipc.IClientInterface;

/**
 * Created by anson on 2019/9/14.
 */
public class BaseResponse {

    public final String mRequestKey;
    public final String mParams;
    public final IClientInterface mClientInterface;

    public BaseResponse(String requestKey, String params, IClientInterface mClientInterface) {
        this.mRequestKey = requestKey;
        this.mParams = params;
        this.mClientInterface = mClientInterface;
    }
}
