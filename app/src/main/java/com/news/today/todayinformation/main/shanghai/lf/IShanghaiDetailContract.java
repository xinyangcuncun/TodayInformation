package com.news.today.todayinformation.main.shanghai.lf;

import com.anson.abc.mvp.mvp.ILifeCircle;
import com.anson.abc.mvp.mvp.IMvpView;
import com.anson.abc.mvp.mvp.MvpControler;

/**
 * Created by anson on 2019/1/13.
 */
public interface IShanghaiDetailContract {
    interface Iview extends IMvpView {


    }

    interface IPresenter extends ILifeCircle {

        void getNetData();
    }

    IShanghaiDetailContract.Iview emptyView = new IShanghaiDetailContract.Iview() {

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
