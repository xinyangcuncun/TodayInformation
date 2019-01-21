package com.news.today.todayinformation.main.shanghai.presenter;

import android.util.Log;

import com.anson.abc.task.LfTask;
import com.news.today.todayinformation.base.BasePresenter;
import com.news.today.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.news.today.todayinformation.main.shanghai.module.ShangHaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by anson on 2019/1/13.
 */
public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.Iview> implements IShanghaiDetailContract.IPresenter{

    public ShanghaiDetailPresenter(IShanghaiDetailContract.Iview view) {
        super(view);
    }

    @Override
    protected IShanghaiDetailContract.Iview getEmptyView() {
        return IShanghaiDetailContract.emptyView;
    }

    @Override
    public void getNetData() {

        //1、数据的结果解析怎么来做
        //2、相同任务的去重工作
            submitTask(new LfTask() {
                //一定要回调到主线程
                @Override
                public void onSuccess(Object o) {
                    //获取网络结果
                    Log.e("getNetData", o.toString());

                }

                @Override
                public void onException(Throwable throwable) {
                    Log.e("getNetData",throwable.toString());
                }

                //运行与子线程
                @Override
                public Object onBackground() {
                    Response response = (Response) new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
                    String strResponse = null;
                    try {
                        strResponse =  response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return strResponse;
                }
            });
        }

}
