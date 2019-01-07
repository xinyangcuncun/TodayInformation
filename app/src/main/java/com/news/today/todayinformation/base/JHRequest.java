package com.news.today.todayinformation.base;

import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.annotation.RequestMethod;
import com.anson.abc.http.request.LfRequest;

/**
 * Created by anson on 2019/1/5.
 */
public class JHRequest extends LfRequest{

    public static IRequest sendHttp(String path, @RequestMethod int requestMethod) {
        JHRequest request = new JHRequest();
        request.host = HostManager.jhHost;
        request.path = path;
        request.requestMethod = requestMethod;
        return request;
    }
}
