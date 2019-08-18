package com.news.today.player.source;

/**
 * Created by anson on 2019/6/30.
 */
public interface  IPlayerSource {

    void setUrl(String url) ;

    String getUrl();

    int getResId();
}
