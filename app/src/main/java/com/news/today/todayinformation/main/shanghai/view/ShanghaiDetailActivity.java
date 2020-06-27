package com.news.today.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.news.today.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.news.today.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;
import com.web.god.ipc.CallBack;
import com.web.god.ipc.IpcManager;
import com.web.god.ipc.requst.IpcRequest;
import com.web.god.ipc.result.IResult;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anson on 2018/12/12.
 */
@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.Iview{

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);

    public static String mActivityOptionsCompat = "ShanghaiDetailActivity";
    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    @Override
    public void afterBindView() {
        initAnima();
//        initReceiver();
//        initProcessData();
        initGetNetData();
//        initPostNetData();
//        initProviderData();
//        initProcessService();
        initIpc();
    }

    private void initIpc() {
        IpcRequest request = new IpcRequest("shanghaiDetail");
        IpcManager.getInstance(this).excuteAsync(request, new CallBack() {
            @Override
            public void callBack(IResult result) {
                String data = result.data();
                Log.e("数据请求", data);
            }
        });

    }


    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient(); // okhttp 配置一些默认
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "0f08cd674792667feb5ce236ea028747");
        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/lottery/types")
                .post(builder.build())
                .build(); //建造者设计模式
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("initGetNetData","onFailure" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("initGetNetData","onResponse" + response.body().string());
            }
        });
    }

    /**
     * 发送网络请求数据
     */
    private void initGetNetData() {
//        ivShanghaiDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.getNetData();
//            }
//        });
//        GetXiaoHuaTask task = new GetXiaoHuaTask();
//        task.execute("desc", "1", "1");
//        Object desc = new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
//        if (desc instanceof Response) {
//            Response response = (Response) desc;
//            Log.e("initGetNetData", response.body().toString());
//        }
        //1、 可以隔离
        OkHttpClient client = new OkHttpClient(); // okhttp 配置一些默认
        //2、构建请求 1）url 2）参数
        HttpUrl.Builder builder = HttpUrl.parse("https://www.baidu.com").newBuilder();
//        builder.addQueryParameter("sort", "desc");
//        builder.addQueryParameter("page", "1");
//        builder.addQueryParameter("pagesize", "1");
//        builder.addQueryParameter("time", "" + System.currentTimeMillis()/1000);
//        builder.addQueryParameter("key", "bbc57dd5e4f05991aff09eafd2e667e0");
        //3、构建Request
        Request request = new Request.Builder()
                .url(builder.build())
                .get()
                .build(); //建造者设计模式
        //4、构建Call
        Call call = client.newCall(request);
        //5 执行网络请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("initGetNetData","onFailure" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("initGetNetData","onResponse" + response.body().string());
            }
        });
    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ViewCompat.setTransitionName(ivShanghaiDetail,mActivityOptionsCompat);
            //开启转场动画
            startPostponedEnterTransition();
        }
    }

    /**
     * 用于 Android 5.0 系统的 界面转场动画  ：共享元素动画
     */
    public static void start_5_0(Activity activity,View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity,ShanghaiDetailActivity.class);
            Pair pair = new Pair(view,mActivityOptionsCompat);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity,intent,optionsCompat.toBundle());
        }
    }

    @Override
    public void showData(ShangHaiDetailBean data) {

    }
}
