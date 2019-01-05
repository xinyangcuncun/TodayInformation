package com.news.today.todayinformation.base;

import com.anson.abc.http.request.host.IHost;

/**
 * Created by anson on 2019/1/5.
 */
public interface HostManager {

    IHost jhHost = new IHost() {

        @Override
        public String getHost() {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath() {
            return "/joke/content/list.php";
        }
    };
}
