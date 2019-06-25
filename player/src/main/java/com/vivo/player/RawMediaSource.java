package com.vivo.player;

import com.vivo.player.lf.IMediaSource;

import java.io.File;

/**
 * author : renchangcun
 * date : 2019/6/25 11:30
 * description :
 */
public class RawMediaSource implements IMediaSource {

    String url;

    @Override
    public void setRemoteUrl(String url) {
        this.url = url;
    }

    public IMediaSource setRawResouce(String packageName,int rawID) {

        setRemoteUrl("android.resource://" + packageName + File.separator + rawID);
        return this;
    }

    @Override
    public String getRemoteUrl() {
        return url;
    }
}
