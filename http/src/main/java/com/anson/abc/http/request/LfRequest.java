package com.anson.abc.http.request;

import com.anson.abc.http.annotation.RequestMethod;
import com.anson.abc.http.request.host.IHost;

/**
 * Created by anson on 2019/1/5.
 */
public class LfRequest implements IRequest{

    protected IHost host;

    @RequestMethod
    protected int requestMethod;

}
