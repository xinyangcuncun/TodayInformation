package com.web.god.plugin;

import android.content.Context;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by anson on 2020-06-14.
 */
@GlideModule
public class TodayNewsGlideModule  extends AppGlideModule {

    //默认底层返回的是Ture,作用是当前的配置只加载一遍
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    //默认底层实现的是空函数,可以全局设置监听、日志、内存等缓存大小
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//        builder.setMemoryCache(new LruResourceCache(1024*1024));
    }

    //默认是空实现,Registry可以给我们定制 资源类型及 资源加载器
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.append(TodayNewsImage.class, InputStream.class, new TodayNewsModuleFactory());
    }
}
