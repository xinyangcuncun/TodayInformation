package com.news.today.todayinformation.main.shenzhen.view;

import com.news.today.todayinformation.BR;
import com.news.today.todayinformation.R;
import com.news.today.todayinformation.databinding.ActivityMvvmBinding;
import com.news.today.todayinformation.main.shenzhen.viewmodel.MvvmModel;
import com.news.today.todayinformation.main.shenzhen.viewmodel.MvvmModel2;
import com.web.god.jetpack.mvvm.BaseMvvmAcivity;
import com.web.god.jetpack.mvvm.MvvmViewInject;

/**
 * Created by anson on 2020-07-26.
 */
@MvvmViewInject(mainlayoutid = R.layout.activity_mvvm,
        viewModelId = {BR.mvvm,BR.mvvm2},
        viewModelClass = {MvvmModel.class, MvvmModel2.class})
public class MvvmActivity extends BaseMvvmAcivity<ActivityMvvmBinding> {
    // mvvm 和 mvp 的区别
    /**
     * 1\mvp 适合入门
     * 2\mvvm 基本上彻底 实现 Activity层的解放
     * 3\在同等情况下，mvvm框架中ViewModel 要好于 p层的设计，可以在Activity 和 Fragment 中达到 ViewModel及数据共享
     * 4\mvvm可以减少 像 mvp那样的接口调用
     */
}
