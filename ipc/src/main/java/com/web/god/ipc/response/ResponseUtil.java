package com.web.god.ipc.response;

import com.web.god.ipc.IClientInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by anson on 2019/9/14.
 */
public class ResponseUtil {

    /**
     * 反射调用的类 必须在 com.ipc.response 包名下面
     * @param requestKey
     * @param requestParmas
     * @param mClientInterface
     */
    public static void getAsyncResponse(String requestKey, String requestParmas, IClientInterface mClientInterface) {
        StringBuilder stringBuilder = new StringBuilder("com.ipc.response.");
        String first = requestKey.substring(0, 1);
        String second = requestKey.substring(1, requestKey.length());
        stringBuilder.append(first.toUpperCase()).append(second).append("Response");
        //通过反射的方式调用的 宿主的类里面
        try {
            Class<?> clazz = Class.forName(stringBuilder.toString());
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class, IClientInterface.class);
            Object object = constructor.newInstance(requestKey, requestParmas, mClientInterface);
            Method method = clazz.getMethod(requestKey);
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
