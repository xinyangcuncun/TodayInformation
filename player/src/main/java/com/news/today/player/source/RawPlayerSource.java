package com.news.today.player.source;

import java.io.File;

/**
 * Created by anson on 2019/6/30.
 */
public class RawPlayerSource implements IPlayerSource{

    private String url;
    private int resId;

    //"android.resource://" + getPackageName() + File.separator + R.raw.splash)
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    public IPlayerSource setPath(String packageName, int rawId) {
        setUrl("android.resource://" + packageName + File.separator + rawId);
        setResId(rawId);
        return this;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
