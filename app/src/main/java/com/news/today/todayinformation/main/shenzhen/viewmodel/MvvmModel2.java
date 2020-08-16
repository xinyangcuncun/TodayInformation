package com.news.today.todayinformation.main.shenzhen.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.web.god.jetpack.mvvm.BaseMvvmModel;

/**
 * Created by anson on 2020-07-26.
 */
public class MvvmModel2 extends BaseMvvmModel {


    public MutableLiveData<String> name2 = new MutableLiveData<>();

    public MvvmModel2(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onResume() {
        name2.setValue("MvvmModel2");
    }

    public void tvMvvmOnclick2(View view) {
        name2.setValue("tvMvvmOnclick2");
        //弹出一个Toast
        getUILiveData().showToast("tvMvvmOnclick2");
    }
}
