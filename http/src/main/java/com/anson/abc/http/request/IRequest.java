package com.anson.abc.http.request;

import com.anson.abc.http.parser.IParser;
import com.anson.abc.http.request.host.IHost;

import java.lang.reflect.Type;
import java.util.Map; /**
 * Created by anson on 2019/1/5.
 */
public interface IRequest {

    void setParams(Map<String, Object> params);

    Map<String, Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();

    IParser getParser();

    Type getType();
}
