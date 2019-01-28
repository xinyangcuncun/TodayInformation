package com.news.today.todayinformation.base;

import com.anson.abc.http.annotation.RequestMethod;
import com.anson.abc.http.parser.DefaultResultParser;
import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.request.LfRequest;

import java.lang.reflect.Type;

/**
 * Created by anson on 2019/1/5.
 */
public class JHRequest extends LfRequest{

    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type) {
        JHRequest request = new JHRequest();
        request.host = HostManager.jhHost;
        request.path = path;
        request.requestMethod = requestMethod;
        request.type = type;
        request.resultParser = DefaultResultParser.getInstance();
        return request;
    }
}
