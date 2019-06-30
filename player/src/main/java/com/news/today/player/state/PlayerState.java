package com.news.today.player.state;

/**
 * Created by anson on 2019/6/30.
 */
public enum PlayerState {

    /**
        播放器处于 未初始化
     */
    IDLE,

    /**
     * 正在准备
     */
    PREPARING,

    /**
     * 准备完成，可以开始播放了
     */
    PREPARED,

    /**
     * 播放中
     */
    STARTED,

    /** 暂停状态 **/
    PAUSED,

    /** 停止状态 **/
    STOPPED,

    /** 完成状态 **/
    COMPLETED,

    /**
     * 停止状态
     *
     **/
    END,

    /** 错误状态 **/
    ERROR

}
