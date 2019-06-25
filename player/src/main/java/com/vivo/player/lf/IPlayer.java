package com.vivo.player.lf;

import android.content.Context;
import android.view.SurfaceView;

import com.vivo.player.state.PlayerState;


/**
 * 播放器接口
 *
 * Created by anson on 2017/11/24.
 */

public interface IPlayer {

    /**
     * 设置显示的SurfaceVIew
     *
     * @param surfaceView
     */
    void setDisplay(SurfaceView surfaceView);

    /**
     * 设置播放器回调,当有新的回调被设置时，播放器必须将当前的状态立即通知给回调(如播放状态，时长，播放进度，音量，视频宽高，缓冲等)
     *
     * @param callback
     */
    void setCallback(IPlayerListener callback);

    /**
     * 重置播放器状态
     */
    void reset();

    /**
     * 准备播放器
     *
     * @param source   要播放的数据源
     */
    void prepare(Context context,IMediaSource source);

    /**
     * 开始播放
     */
    void start();

    /**
     * 暂停播放
     */
    void pause();

    /**
     * 停止播放
     */
    void stop();

    /**
     * 拖动
     */
    void seek(int msec);

    /**
     * 释放播放器
     */
    void release();

    /**
     * 获取当前播放器状态
     *
     * @return
     */
    PlayerState getState();

    /**
     * 设置音量(0-100)
     *
     * @param volume
     */
    void setVolume(int volume);

    /**
     * 获取当前音量(0-100)
     *
     * @return
     */
    int getVolume();

    /**
     * 获得当前播放时间(毫秒)
     *
     * @return
     */
    int getCurrentPosition();

    /**
     * 启用/禁用播放时间回调
     *
     * @param enabled
     *
     */
    void enablePositionReport(boolean enabled);

    /**
     * 是否支持显示缓冲进度
     *
     * @return
     */
    boolean supportBufferProgress();

    /**
     * 获取视频宽度
     *
     * @return
     */
    int getVideoWidth();

    /**
     * 获取视频高度
     *
     * @return
     */
    int getVideoHeight();
}
