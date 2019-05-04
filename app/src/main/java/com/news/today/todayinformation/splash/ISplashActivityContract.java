package com.news.today.todayinformation.splash;

import com.anson.abc.mvp.mvp.ILifeCircle;
import com.anson.abc.mvp.mvp.IMvpView;
import com.web.god.annotation.MvpEmptyViewFactory;

/**
 * Created by anson on 2018/10/28.
 */
public interface ISplashActivityContract {

    @MvpEmptyViewFactory
    interface Iview extends IMvpView {
        void setTvTimer(String timer);
    }

    interface IPresenter extends ILifeCircle {
        void initTimer();
    }

//    Iview emptyView = new Iview() {
//        @Override
//        public void setTvTimer(String timer) {
//
//        }
//
//
//        @Override
//        public MvpControler getMvpControler() {
//            return null;
//        }
//    };

}
