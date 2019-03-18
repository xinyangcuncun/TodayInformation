package com.news.today.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * Created by anson on 2019/1/26.
 */
public class ShangHaiDetailBean {
    public int error_code;
    public String reason;
    public XiaoHuaListBean result;

    public static class  XiaoHuaListBean{
        public ArrayList<XiaoHuaBean> data;
    }

//     "content": "有一天晚上我俩一起吃西瓜，老大把西瓜籽很整洁的吐在了一张纸上，\r\n过了几天，我从教室回但宿舍看到老大在磕瓜子，\r\n我就问他：老大，你什么时候买的瓜子？\r\n老大说：刚晒好，说着抓了一把要递给我……",
//             "hashId": "bcc5fdc2fb6efc6db33fa242474f108a",
//             "unixtime": 1418814837,
//             "updatetime": "2014-12-17 19:13:57"
    public static class XiaoHuaBean{
       public String content;
       public String hashId;
       public String unixtime;
       public String updatetime;
    }
}
