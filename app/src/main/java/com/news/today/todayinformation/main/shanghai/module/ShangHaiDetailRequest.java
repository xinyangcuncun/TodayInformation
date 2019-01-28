package com.news.today.todayinformation.main.shanghai.module;

import com.anson.abc.http.annotation.RequestMethod;
import com.anson.abc.http.request.IRequest;
import com.news.today.todayinformation.base.JHRequest;
import com.news.today.todayinformation.main.shanghai.dto.ShangHaiDetailBean;

/**
 * Created by anson on 2019/1/5.
 */

public interface ShangHaiDetailRequest {
    IRequest xiaoHuaRequest = JHRequest.sendHttp("/joke/content/list.php", RequestMethod.Get, ShangHaiDetailBean.class);
}
