package com.web.god.jetpack.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by anson on 2020-07-26.
 */
public class BaseMvvmModel extends AndroidViewModel implements LifecycleObserver {

    public MutableLiveData<String> toastLiveData = new MutableLiveData<>();
    private UILiveData mUILiveData;

    public BaseMvvmModel(@NonNull Application application) {
        super(application);
    }

    //生命周期的绑定
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }

    public UILiveData getUILiveData(){
        if (mUILiveData == null) {
            mUILiveData = new UILiveData();
        }
        return mUILiveData;
    }

    public class UILiveData{
        public void showToast(String text) {
            toastLiveData.setValue(text);
        }
    }
}
