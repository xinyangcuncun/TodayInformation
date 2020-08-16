package com.web.god.jetpack.mvvm;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by anson on 2020-07-26.
 */
public abstract class BaseMvvmAcivity<V extends ViewDataBinding> extends AppCompatActivity {

    protected V viewDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvvmViewInject annotation = this.getClass().getAnnotation(MvvmViewInject.class);
        if (annotation != null) {
            int mainlayoutid = annotation.mainlayoutid();
            int[] ids = annotation.viewModelId();
            Class<? extends BaseMvvmModel> [] viewModelClass = annotation.viewModelClass();
            if (mainlayoutid > 0 && ids.length == viewModelClass.length) {
                bindView(mainlayoutid,ids,viewModelClass);
                afterBindView();
            } else {
                throw new RuntimeException("params error");
            }
        } else {
            throw new RuntimeException("annotation  = null");
        }
    }

    private void bindView(int mainlayoutid,int[] ids,Class<? extends BaseMvvmModel>[] viewModelClass) {
        viewDataBinding = DataBindingUtil.setContentView(BaseMvvmAcivity.this, mainlayoutid);
        viewDataBinding.setLifecycleOwner(this);
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            Class<? extends BaseMvvmModel> clazz = viewModelClass[i];
            BaseMvvmModel viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(clazz);
            viewDataBinding.setVariable(id, viewModel);
            getLifecycle().addObserver(viewModel);
            bindViewModel(viewModel);
        }
    }

    private void bindViewModel(BaseMvvmModel viewModel) {
        viewModel.toastLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(BaseMvvmAcivity.this,s,Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void afterBindView() {

    }

}
