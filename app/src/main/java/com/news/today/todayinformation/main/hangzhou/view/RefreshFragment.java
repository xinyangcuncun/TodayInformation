package com.news.today.todayinformation.main.hangzhou.view;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.web.god.refresh.GodRefreshLayout;

import butterknife.BindView;

/**
 * Created by anson on 2019/3/10.
 */
@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment {

    @BindView(R.id.god_refresh)
    GodRefreshLayout godRefresh;

    @Override
    public void afterBindView() {
        godRefresh.setRefreshManager();
        godRefresh.setRefreshListener(new GodRefreshLayout.RefreshingListener() {
            @Override
            public void onRefreshing() {
                godRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        godRefresh.refreshOver();
                    }
                }, 2000);
            }
        });
    }
}
