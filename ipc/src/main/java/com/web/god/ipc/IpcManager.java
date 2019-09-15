package com.web.god.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import com.web.god.ipc.requst.IRequest;
import com.web.god.ipc.result.IResult;
import com.web.god.ipc.result.IpcResult;
import com.web.god.ipc.server.IpcService;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by anson on 2019/8/29.
 */
public class IpcManager {


    private static IpcManager mInstance;
    private final Context mContext;
    //指针对异步请求-------------
    private Set<IRequest> mRequests = new TreeSet<IRequest>();
    private Set<IRequest> mWaitRequests = new TreeSet<IRequest>();
    //--------------------
    private int mConnetStatus = IConnectStatus.STATUS_UNBIND;
    private ServiceConnection mConnection;
    private IServerInterface mServer;
    private IBinder.DeathRecipient mDeathRecipient;
    private IClientInterface.Stub mClientInterface;

    interface IConnectStatus{
        int STATUS_UNBIND = 0;
        int STATUS_BINDING =1;
        int STATUS_BIND = 2;
    }

    private IpcManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public synchronized static IpcManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new IpcManager(context);
        }
        return mInstance;
    }

    //同步请求
    public IResult excuteSync(IRequest request) {
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequests.contains(request)) {
            return IpcResult.getErrorResult();
        }
        //判断服务是否已经连接成功
        if (mConnetStatus != IConnectStatus.STATUS_BIND) {
            connectService();
            return IpcResult.getErrorResult();
        }
        return excute(request);
    }

    // TODO: 2019/9/8 提供给客户端  建立连接的一个方法
    public void initConnet() {
        // 大家可以自由完善
    }

    //异步跨进程通信
    public void excuteAsync(IRequest request,CallBack callBack) {
        if (TextUtils.isEmpty(request.getRequestKey()) || mRequests.contains(request)) {
            callBack.callBack(IpcResult.getErrorResult());
            return;
        }
        //保存这个request
        request.addCallBack(callBack);
        mRequests.add(request);
        //判断服务是否已经连接成功
        if (mConnetStatus != IConnectStatus.STATUS_BIND) {
            connectService();
            mWaitRequests.add(request);
            return;
        }
        excute(request);
    }

    //建立IPC 通讯连接
    private void connectService() {
        if (mConnection == null) {
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    mConnetStatus = IConnectStatus.STATUS_BIND;
                    mServer = IServerInterface.Stub.asInterface(service);
                    //往服务端注入接口
                    try {
                        mServer.registerCallBack(mClientInterface);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //Binder 通信的死亡通知  有重启逻辑
                    try {
                        service.linkToDeath(mDeathRecipient,0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //连接成功之后 去把等待的数据请求 发送
                    for (IRequest request : mWaitRequests) {
                        excute(request);
                    }
                    mWaitRequests.clear();

                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mConnetStatus = IConnectStatus.STATUS_UNBIND;
                }
            };
        }
        if (mDeathRecipient == null){
            mDeathRecipient = new IBinder.DeathRecipient(){

                @Override
                public void binderDied() {
                    mConnetStatus = IConnectStatus.STATUS_UNBIND;
                    //针对异步请求做CallBack处理
                    for (IRequest request : mRequests) {
                        request.getCallBack().callBack(IpcResult.getErrorResult());
                    }
                    mRequests.clear();
                }
            };
        }

        if (mClientInterface == null){
            mClientInterface = new IClientInterface.Stub(){

                @Override
                public void callBack(String requestKey, String resultStr) throws RemoteException {
                    Iterator<IRequest> iterator = mRequests.iterator();
                    while (iterator.hasNext()) {
                        IRequest next = iterator.next();
                        if (TextUtils.equals(next.getRequestKey(), requestKey)) {
                            next.getCallBack().callBack(IpcResult.getSuccessResult(resultStr));
                            mRequests.remove(next);
                            return;
                        }
                    }
                }
            };
        }
        Intent intent = new Intent(mContext, IpcService.class);
        mContext.bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
        mConnetStatus = IConnectStatus.STATUS_BINDING;
    }

    //实际 跨进程通讯的方法
    private IResult excute(IRequest request) {
        if (request.getCallBack() != null) {
            try {
                mServer.excuteAsync(request.getRequestKey(), request.getParams());
            } catch (RemoteException e) {
                request.getCallBack().callBack(IpcResult.getErrorResult());
            }
        } else {
            try {
                String result = mServer.excuteSync(request.getRequestKey(), request.getParams());
                return IpcResult.getSuccessResult(result);
            } catch (RemoteException e) {
               return IpcResult.getErrorResult();
            }
        }
        return IpcResult.getErrorResult();
    }
}
