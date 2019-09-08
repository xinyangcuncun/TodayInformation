package com.web.god.ipc.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.web.god.ipc.IClientInterface;
import com.web.god.ipc.IServerInterface;

/**
 * Created by anson on 2019/8/29.
 */
public class IpcService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IServerInterface.Stub() {

            public IClientInterface mClientInterface;

            @Override
            public void excuteAsync(String requestKey, String requestParmas) throws RemoteException {
                switch (requestKey) {
                    case "shanghai_detail":
                        if (mClientInterface != null) {
                            mClientInterface.callBack(requestKey,"来自远方的祝福");
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public String excuteSync(String requestKey, String requestParmas) throws RemoteException {
                String result = "";
                switch (requestKey) {
                    case "shanghai_detail":
                        result = "来自远方的祝福";
                        break;
                    default:
                        break;
                }
                return result;
            }

            @Override
            public void registerCallBack(IClientInterface iClientInterface) throws RemoteException {
                this.mClientInterface = iClientInterface;
            }
        };
    }
}
