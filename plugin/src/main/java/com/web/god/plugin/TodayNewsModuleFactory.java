package com.web.god.plugin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

/**
 * Created by anson on 2020-06-14.
 */
public class TodayNewsModuleFactory implements ModelLoaderFactory<TodayNewsImage, InputStream> {


    @NonNull
    @Override
    public ModelLoader<TodayNewsImage, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new ModelLoader<TodayNewsImage, InputStream>() {
            //Glide 采用 okhttp 该如何开发？
            @Nullable
            @Override
            public LoadData<InputStream> buildLoadData(@NonNull TodayNewsImage todayNewsImage, int width, int height, @NonNull Options options) {
                return new LoadData<InputStream>(new ObjectKey(todayNewsImage.getUrl()),new HttpUrlFetcher(new GlideUrl(todayNewsImage.getUrl()),10000));
            }


            @Override
            public boolean handles(@NonNull TodayNewsImage todayNewsImage) {
                return true;
            }
        };
    }

    @Override
    public void teardown() {

    }
}
