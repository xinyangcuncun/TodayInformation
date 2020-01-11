package com.anson.abc.http.okhttp;

import com.anson.abc.http.HttpScheduler;
import com.anson.abc.http.annotation.RequestMethod;
import com.anson.abc.http.https.Https;
import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.request.call.ICall;

import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by anson on 2019/1/6.
 */
public class OkHttpScheduler extends HttpScheduler{

    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest request) {
        Map<String, Object> params = request.getParams();
        int requestMethod = request.getRequestMethod();
        Request.Builder requestuilder = new Request.Builder();
        switch (requestMethod) {
            case RequestMethod.Get:
                //拼接Get请求的url host + path
                StringBuilder urlStrBuilder = new StringBuilder(request.getHost().getHost());
                urlStrBuilder.append(request.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStrBuilder.toString()).newBuilder();
                if (params != null && params.size() > 0) {
                    Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> next = iterator.next();
                        // REMAKE: 待重构 2019/1/6  这里涉及对象如何转成String 字符串
                        urlBuilder.addQueryParameter(next.getKey(), String.valueOf(next.getValue()));
                    }
                }
                requestuilder.get().url(urlBuilder.build());
                break;

                case RequestMethod.Post:
                    // REMAKE: 待重构 2019/1/6  留给大家去完善
                    break;
        }
        Request okHttpRequest = requestuilder.build();
        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(request,call);
        return okHttpCall;
    }

    private OkHttpClient getClient() {
        if (client == null) {
//            client = new OkHttpClient();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(Https.getSSLSocketFactory());
//            Https2.SSLParams sslSocketFactory = Https2.getSslSocketFactory(null, null, null);
//            builder.sslSocketFactory(sslSocketFactory.sSLSocketFactory,sslSocketFactory.trustManager);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            client = builder.build();
        }
        return client;
    }
}
