package com.vivo.player;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;


import com.vivo.player.factory.MediaPlayerFactory;
import com.vivo.player.lf.IMediaSource;
import com.vivo.player.lf.IPlayer;
import com.vivo.player.lf.IPlayerError;
import com.vivo.player.lf.IPlayerListener;
import com.vivo.player.state.PlayerState;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anson on 2018/5/9.
 */

public class MusicPlayerService extends Service implements IPlayerListener {

    private IBinder mBinder = new LocalBinder();
    private HashMap<String, IPlayerListener> mListeners;
    private SoftReference<HashMap<String, IPlayerListener>> weakReference;
    private PlayerState mState = PlayerState.IDLE;
    private MediaPlayerFactory mPlayerFactory;
    private IPlayer mPlayer;
    private int mType;


    public class LocalBinder extends Binder {

        public MusicPlayerService getService () {
            return MusicPlayerService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //获取配置信息
        mType = getMetaDataIdFromAct();

    }


    private int getMetaDataIdFromAct() {
        int resId= 0;
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            resId= appInfo.metaData.getInt("player_type");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resId;
    }

    private interface CallbackInvoker {
        void invoke(IPlayerListener callback);
    }

    private void doCallbackSafely (CallbackInvoker invoker) {
        synchronized (this) {
            if (weakReference != null && weakReference.get() != null) {
                for (IPlayerListener listener : weakReference.get().values()) {
                    if (listener != null) {
                        invoker.invoke(listener);
                    }
                }
            }
        }
    }

    //播放器回调
    @Override
    public void onStateChanged(PlayerState state) {
        //同步此时的播放状态
        mState = state;
        //同步所有子节点
        doCallbackSafely(new CallbackInvoker() {
            @Override
            public void invoke( IPlayerListener callback) {
                callback.onStateChanged(mState);
            }
        });
    }

    @Override
    public void onDurationChanged(final int msec) {

        doCallbackSafely(new CallbackInvoker() {
            @Override
            public void invoke( IPlayerListener callback) {
                callback.onDurationChanged(msec);
            }
        });
    }

    @Override
    public void onSeekComplete() {
        doCallbackSafely(new CallbackInvoker() {
            @Override
            public void invoke( IPlayerListener callback) {
                callback.onSeekComplete();
            }
        });
    }

    @Override
    public void onError(final IPlayerError error) {
        doCallbackSafely(new CallbackInvoker() {
            @Override
            public void invoke( IPlayerListener callback) {
                callback.onError(error);
            }
        });
    }


    //----------------------对外Api 区域--------------------------

    //添加监听
    public void setVideoPlayCallBack(String key,IPlayerListener loadInfoListener) {
        if (mListeners == null) {
            mListeners = new HashMap<>();
        }
        if (weakReference == null) {
            weakReference = new SoftReference<>(mListeners);
        }
        weakReference.get().put(key, loadInfoListener);
    }

    //移除监听
    public void removeVideoPlayCallback(String key) {
        if (weakReference != null && weakReference.get() != null) {
            for (Map.Entry<String, IPlayerListener> entry : weakReference.get().entrySet()) {
                if (TextUtils.equals(entry.getKey(), key)) {
                    weakReference.get().remove(key);
                    break;
                }
            }
        }
    }
    //处理播放/暂停逻辑
    public void handlePlayOrStop(IMediaSource source) {
        switch (mState) {
            case IDLE:
                if (mPlayer != null) {
                    mPlayer.release();
                    mPlayer = null;
                }
                if (mPlayerFactory == null) {
                    mPlayerFactory = new MediaPlayerFactory();
                }
                if (mPlayer == null) {
                    mPlayer = mPlayerFactory.createPlayer(mType);
                }
                mPlayer.prepare(this,source);
                mPlayer.setCallback(this);
                break;
            case STARTED:
                //暂停播放
                if (mPlayer != null) {
                    mPlayer.pause();
                }
                break;
            case PAUSED:
                //开始播放
                if (mPlayer != null) {
                    mPlayer.start();
                }
                break;
            case COMPLETED:
            case ERROR:
                //重新播放

                break;
        }
    }
}
