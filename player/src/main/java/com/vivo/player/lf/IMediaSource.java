package com.vivo.player.lf;

/**
 * 播放数据源
 *
 * Created by anson on 2017/11/24.
 */

public interface IMediaSource {

    void setRemoteUrl(String url);

    String getRemoteUrl();
}
