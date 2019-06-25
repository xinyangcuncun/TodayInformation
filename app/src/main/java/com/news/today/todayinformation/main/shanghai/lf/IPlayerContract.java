package com.news.today.todayinformation.main.shanghai.lf;

import android.content.Context;

import com.anson.abc.mvp.mvp.ILifeCircle;
import com.anson.abc.mvp.mvp.IMvpView;
import com.news.today.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.web.god.annotation.MvpEmptyViewFactory;

/**
 * Created by anson on 2019/1/13.
 */
public interface IPlayerContract {
    @MvpEmptyViewFactory
    interface Iview extends IMvpView {


    }

    interface IPresenter extends ILifeCircle {


        void bindPlayerService(Context context);
    }

}
