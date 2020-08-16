package com.news.today.todayinformation.main.shenzhen.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.web.god.jetpack.mvvm.BaseMvvmModel;

/**
 * Created by anson on 2020-07-26.
 */
public class MvvmModel extends BaseMvvmModel {


    public ObservableField<String> name = new ObservableField<>();

    public MvvmModel(@NonNull Application application) {
        super(application);
    }

    public void tvMvvmOnclick(View view) {
        name.set("tvMvvmOnclick");
        //弹出一个Toast
       getUILiveData().showToast("tvMvvmOnclick");
    }

    @Override
    public void onResume() {
       name.set("MvvmModel");
    }
}
