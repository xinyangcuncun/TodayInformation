package com.anson.abc.http.request;

import com.anson.abc.http.parser.IParser;
import com.anson.abc.http.annotation.RequestMethod;
import com.anson.abc.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by anson on 2019/1/5.
 */
public class LfRequest implements IRequest{


    protected String path;

    protected IHost host;

    protected Map<String, Object> params;

    protected Type type;

    protected IParser resultParser;

    @RequestMethod
    protected int requestMethod;

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public IParser getParser() {
        return resultParser;
    }

    @Override
    public Type getType() {
        return type;
    }
}
