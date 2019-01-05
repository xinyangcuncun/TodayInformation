package com.news.today.todayinformation.splash;

import com.anson.abc.mvp.mvp.base.BaseMvpPresenter;

/**
 * Created by anson on 2018/10/13.
 */
public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.Iview> implements ISplashActivityContract.IPresenter{


    private CustomCountDownTimer timer;

    public SplashTimerPresenter(ISplashActivityContract.Iview view) {
        super(view);
    }


    public void initTimer() {
        timer = new CustomCountDownTimer(5, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                getView().setTvTimer(time + "秒");
            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");
            }
        });
        timer.start();
    }

    public void cancel() {
        timer.cancel();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();
    }

    /**
     * 防止 空指针异常
     * @return
     */
    @Override
    protected ISplashActivityContract.Iview getEmptyView() {
        return ISplashActivityContract.emptyView;
    }
}
