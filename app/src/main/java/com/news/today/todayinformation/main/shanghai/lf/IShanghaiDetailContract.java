package com.news.today.todayinformation.main.shanghai.lf;

import com.anson.abc.mvp.mvp.ILifeCircle;
import com.anson.abc.mvp.mvp.IMvpView;
import com.news.today.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.web.god.annotation.MvpEmptyViewFactory;

/**
 * Created by anson on 2019/1/13.
 */
public interface IShanghaiDetailContract {
    @MvpEmptyViewFactory
    interface Iview extends IMvpView {


        void showData(ShangHaiDetailBean data);
    }

    interface IPresenter extends ILifeCircle {

        void getNetData(int pagesize);
    }

//    IShanghaiDetailContract.Iview emptyView = new IShanghaiDetailContract.Iview() {
//
//        @Override
//        public void showData(ShangHaiDetailBean data) {
//
//        }
//
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };
}
