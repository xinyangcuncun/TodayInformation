package com.news.today.todayinformation.main.shanghai.presenter;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.anson.abc.http.result.IResult;
import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BasePresenter;
import com.news.today.todayinformation.base.ContextHelper;
import com.news.today.todayinformation.base.JHTask;
import com.news.today.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.news.today.todayinformation.main.shanghai.lf.IPlayerContract;
import com.news.today.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.news.today.todayinformation.main.shanghai.module.ShangHaiDetailHttpTask;
import com.vivo.player.MusicPlayerService;
import com.vivo.player.RawMediaSource;
import com.vivo.player.lf.IPlayerError;
import com.vivo.player.lf.IPlayerListener;
import com.vivo.player.state.PlayerState;

/**
 * Created by anson on 2019/1/13.
 */
public class PlayerPresenter extends BasePresenter<IPlayerContract.Iview> implements IPlayerContract.IPresenter, IPlayerListener {

    private MusicPlayerService videoPlayerService;

    public PlayerPresenter(IPlayerContract.Iview view) {
        super(view);

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            onServiceAttached((MusicPlayerService.LocalBinder)service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            onServiceDetached();
        }
    };

    private void onServiceAttached (MusicPlayerService.LocalBinder service) {
        videoPlayerService = service.getService();
        videoPlayerService.setVideoPlayCallBack(getView().toString(),this);
        videoPlayerService.handlePlayOrStop(new RawMediaSource().setRawResouce(ContextHelper.getAppContext().getPackageName(),R.raw.haha));
    }

    private void onServiceDetached () {
        if (videoPlayerService != null) {
            videoPlayerService.removeVideoPlayCallback(getView().toString());
            videoPlayerService = null;
        }
    }


    @Override
    public void bindPlayerService(Context context) {

        Intent serviceIntent = new Intent(context, MusicPlayerService.class);
        context.bindService(serviceIntent, connection, Service.BIND_AUTO_CREATE);

    }

    @Override
    public void onStateChanged(PlayerState state) {

    }

    @Override
    public void onDurationChanged(int msec) {

    }

    @Override
    public void onSeekComplete() {

    }

    @Override
    public void onError(IPlayerError error) {

    }
}
