// IServerInterface.aidl
package com.web.god.ipc;

import com.web.god.ipc.IClientInterface;

// Declare any non-default types here with import statements

interface IServerInterface {
   void excuteAsync(String requestKey,String requestParmas);

   String excuteSync(String requestKey,String requestParmas);

   void registerCallBack(IClientInterface iClientInterface);
}
