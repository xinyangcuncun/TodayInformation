package com.news.today.todayinformation.main.shanghai.module;

import com.anson.abc.http.LfHttpServer;
import com.anson.abc.http.result.IResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anson on 2019/1/5.
 */
public class ShangHaiDetailHttpTask<T> extends LfHttpServer {

    public IResult<T> getXiaoHuaList(String sort, String page, String pagesize) {
        Map<String, Object> params = new HashMap<>();
        params.put("sort", sort);
        params.put("page", page);
        params.put("pagesize", pagesize);
        params.put("time", "" + System.currentTimeMillis()/1000);
        params.put("key", "bbc57dd5e4f05991aff09eafd2e667e0");
        return super.execute(ShangHaiDetailRequest.xiaoHuaRequest,params);
    }
}
