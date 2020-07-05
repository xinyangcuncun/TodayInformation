package com.web.god.jetpack.lifeCircle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

/**
 * Created by anson on 2020-06-27.
 */
public class LifeCircleViewModel extends ViewModel implements LifecycleObserver {

    // lifecircle owner 和 lifecircleobserver 如何通信？
    //1\通过LiveData owner 获取 observer的数据
    //2\observer 获取 owner 的数据只能通过对象传递过来

    private MutableLiveData<String> currentName;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void connectListener() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void on_resume() {
        currentName.setValue("on_resume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        Log.e("LifeCircleObserver", "Lifecycle.Event.ON_PAUSE");
    }
}
