package com.web.god.jetpack.lifeCircle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by anson on 2020-06-27.
 */
public class LifeCircleObserver implements LifecycleObserver {

    // lifecircle owner 和 lifecircleobserver 如何通信？
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void connectListener() {

        Log.e("LifeCircleObserver", "Lifecycle.Event.ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        Log.e("LifeCircleObserver", "Lifecycle.Event.ON_PAUSE");
    }
}
