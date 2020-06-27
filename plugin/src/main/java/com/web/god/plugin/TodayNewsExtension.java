package com.web.god.plugin;

import androidx.annotation.NonNull;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;

/**
 * Created by anson on 2020-06-14.
 */
@GlideExtension
public class TodayNewsExtension {

    private TodayNewsExtension(){}

    //自定义的一个拓展
    @NonNull
    @GlideOption
    public static BaseRequestOptions<?> todayNews(BaseRequestOptions<?> options) {
        return options
                .centerInside()
                .override(50);
    }
}
