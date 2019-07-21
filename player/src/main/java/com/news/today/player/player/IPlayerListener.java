package com.news.today.player.player;

import com.news.today.player.state.PlayerState;

/**
 * Created by anson on 2019/7/21.
 */
public interface IPlayerListener {

    void playerStateChanged(PlayerState state);

}
