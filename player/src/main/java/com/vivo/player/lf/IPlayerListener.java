package com.vivo.player.lf;


import com.vivo.player.state.PlayerState;

/**
 * Created by anson on 2018/5/9.
 */

public interface IPlayerListener {

    void onStateChanged(PlayerState state);

    void onDurationChanged(int msec);

    void onSeekComplete();

    void onError(IPlayerError error);
}
