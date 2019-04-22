package com.web.god.refresh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by anson on 2019/4/7.
 */
public class DefaultRefreshManager extends BaseRefreshManager{

    private TextView mTvRefresh;

    public DefaultRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        View view = mLayoutInflater.inflate(R.layout.ulti_header_layout, null, false);
        mTvRefresh = view.findViewById(R.id.header_text);
        return view;
    }

    @Override
    public void downRefresh() {
        mTvRefresh.setText("下拉刷新");

    }

    @Override
    public void releaseRefresh() {
        mTvRefresh.setText("释放刷新");
    }

    @Override
    public void iddleRefresh() {
        mTvRefresh.setText("下拉刷新");
    }

    @Override
    public void refreshing() {
        mTvRefresh.setText("正在刷新");
    }

    @Override
    public void downRefreshPercent(float precent) {

    }
}
