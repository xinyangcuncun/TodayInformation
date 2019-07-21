package com.news.today.player.player;

import android.content.Context;

/**
 * Created by anson on 2019/7/14.
 */
public interface IPlayer {

    /**
     * 播放器释放
     */
    void release();

    void prepare(Context context ,String url);

    void setPlayingListener(IPlayerListener listener);

    void paused();

    void reStart();
}
