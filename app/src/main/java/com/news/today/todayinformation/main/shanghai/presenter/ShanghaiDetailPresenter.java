package com.news.today.todayinformation.main.shanghai.presenter;

import com.anson.abc.task.LfTask;
import com.news.today.todayinformation.base.BasePresenter;
import com.news.today.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.news.today.todayinformation.main.shanghai.module.ShangHaiDetailHttpTask;

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
        submitTask(new LfTask() {
            @Override
            public void onSuccess(Object o) {
                //获取网络结果
            }

            @Override
            public void onException(Throwable throwable) {

            }

            //运行与子线程
            @Override
            public Object onBackground() {
                return new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
            }
        });
    }
}
