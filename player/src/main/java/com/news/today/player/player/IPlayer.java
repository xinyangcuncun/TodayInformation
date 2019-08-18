package com.news.today.player.player;

import android.content.Context;

import com.news.today.player.source.IPlayerSource;

/**
 * Created by anson on 2019/7/14.
 */
public interface IPlayer {

    /**
     * 播放器释放
     */
    void release();

    void prepare(Context context ,IPlayerSource playerSource);

    void setPlayingListener(IPlayerListener listener);

    void paused();

    void reStart();
}
