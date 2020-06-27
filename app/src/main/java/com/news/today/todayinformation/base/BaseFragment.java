package com.news.today.todayinformation.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anson.abc.mvp.mvp.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

/**
 * Created by anson on 2018/7/14.
 */

public abstract class BaseFragment extends LifeCircleMvpFragment {


    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int mainlayoutid = annotation.mainlayoutid();
            if (mainlayoutid > 0) {
                mView = initFragmentView(inflater,mainlayoutid);
                bindView(mView);
                afterBindView();
            } else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        } else {
            throw new RuntimeException("annotation  = null");
        }
        return mView;
    }

    private View initFragmentView(LayoutInflater inflater,int mainlayoutid) {
        return inflater.inflate(mainlayoutid, null);
    }


    // 模板方法 设计模式
    public abstract void afterBindView();

    // View 的依赖注入绑定
    private void bindView(View mView) {
        ButterKnife.bind(this,mView);
    }
}
