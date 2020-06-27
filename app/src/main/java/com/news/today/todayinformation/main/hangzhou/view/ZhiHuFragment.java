package com.news.today.todayinformation.main.hangzhou.view;

import com.google.android.material.appbar.AppBarLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.animation.AnimationUtils;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.hangzhou.adpter.ZhiHuAdapter;
import com.news.today.todayinformation.main.shanghai.dto.ShangHaiDetailBean;
import com.news.today.todayinformation.main.shanghai.lf.IShanghaiDetailContract;
import com.news.today.todayinformation.main.shanghai.presenter.ShanghaiDetailPresenter;

import butterknife.BindView;

/**
 * Created by anson on 2019/3/10.
 */
@ViewInject(mainlayoutid = R.layout.fragment_zhihu)
public class ZhiHuFragment extends BaseFragment implements IShanghaiDetailContract.Iview{

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.zhihu_app_barlayot)
    AppBarLayout zhihuAppBarlayot;
    @BindView(R.id.zhihu_recyclerview)
    RecyclerView zhihuRecyclerview;

    @Override
    public void afterBindView() {
        zhihuRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        zhihuRecyclerview.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.zhihu_recyclerview_show));
        mPresenter.getNetData(20);
    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        if (zhihuRecyclerview.getAdapter() == null) {
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            zhihuRecyclerview.setAdapter(adapter);
        }
    }
}
