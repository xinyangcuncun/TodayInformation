package com.web.god.ipc.requst;

import com.web.god.ipc.CallBack; /**
 * Created by anson on 2019/8/29.
 */
public interface IRequest extends Comparable<IRequest>{

    void setParams(String params);

    String getParams();

    String getRequestKey();

    void addCallBack(CallBack callBack);

    CallBack getCallBack();

    long getAddTime();
}
