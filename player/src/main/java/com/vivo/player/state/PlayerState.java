package com.vivo.player.state;



/**
 * 播放器状态枚举值
 *
 * Created by anson on 2017/11/24.
 */

public enum PlayerState {

    /**

     */
    IDLE,

    /**
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
