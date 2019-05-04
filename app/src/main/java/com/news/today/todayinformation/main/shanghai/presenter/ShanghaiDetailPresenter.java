package com.news.today.todayinformation.main.shanghai.presenter;

import com.anson.abc.http.result.IResult;
import com.news.today.todayinformation.base.BasePresenter;
import com.news.today.todayinformation.base.JHTask;
import com.news.today.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.news.today.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.news.today.todayinformation.main.shanghai.module.ShangHaiDetailHttpTask;

/**
 * Created by anson on 2019/1/13.
 */
public class ShanghaiDetailPresenter extends BasePresenter<IShanghaiDetailContract.Iview> implements IShanghaiDetailContract.IPresenter{

    public ShanghaiDetailPresenter(IShanghaiDetailContract.Iview view) {
        super(view);

    }

//    @Override
//    protected IShanghaiDetailContract.Iview getEmptyView() {
//        return IShanghaiDetailContract.emptyView;
//    }

    @Override
    public void getNetData(final int pagesize) {

        //1、数据的结果解析怎么来做
        //2、相同任务的去重工作
           /* submitTask(new LfTask() {
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
                    IResult desc = new ShangHaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
                    return desc;
                }
            });*/

           /*
              架构师的必备条件
              1、合理利用继承关系
              2、合理利用抽象编程
              3、合理利用泛型传递数据
              4、合理利用一些设计模式
            */
           submitTask(new JHTask<ShangHaiDetailBean>() {
               @Override
               public IResult<ShangHaiDetailBean> onBackground() {
                   return new ShangHaiDetailHttpTask<ShangHaiDetailBean>().getXiaoHuaList("desc", "1", pagesize + "");
               }

               @Override
               public void onSuccess(IResult<ShangHaiDetailBean> t) {
                   ShangHaiDetailBean data = t.data();
//                   Gson gson = new Gson();
//                   String s = gson.toJson(data);
//                   Log.e("ShanghaiDetailPresenter", s);
                   getView().showData(data);
               }
           });
        }


}
