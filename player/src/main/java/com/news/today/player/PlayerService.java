package com.news.today.player;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.news.today.player.source.IPlayerSource;
import com.news.today.player.state.PlayerState;

/**
 * Created by anson on 2019/6/30.
 */
public class PlayerService extends Service{


    private PlayerState mState = PlayerState.IDLE;

    public class PlayerBinder extends Binder {

        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }

    // onStartCommand 在 startService 有作用，start多次就会调用多次，一般用来给Serivice传递数据
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    //onCreate 不管 start 还是 bind 也不管调用几次，只会启动一次，常用来做全局舒适化操作
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void playOrPause(IPlayerSource playerSource) {
        switch (mState) {
            case IDLE:
                //初始化播放器 去播放
                String url = playerSource.getUrl();

                break;
            case PREPARING:
                break;
               default:
                   break;
        }
    }
}
