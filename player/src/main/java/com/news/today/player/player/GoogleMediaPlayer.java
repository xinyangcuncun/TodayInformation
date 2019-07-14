package com.news.today.player.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

/**
 * Created by anson on 2019/7/14.
 */
public class GoogleMediaPlayer implements IPlayer, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    private MediaPlayer mMediaPlayer;

    public GoogleMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnErrorListener(this);
    }
    @Override
    public void release() {

    }

    @Override
    public void prepare(Context context,String url) {
        //操作MediaPlayer
        // medila Player 播放系统资源时  只能用 uri的方式
        try {
            mMediaPlayer.setDataSource(context, Uri.parse(url));
            mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //初始化成功 且播放器准备后 回调
        mp.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e("GoogleMediaPlayer", "what = " + what + " extra = " + extra);
        return false;
    }
}
