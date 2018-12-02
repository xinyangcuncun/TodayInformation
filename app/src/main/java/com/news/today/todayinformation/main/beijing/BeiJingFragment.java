package com.news.today.todayinformation.main.beijing;

import android.widget.TextView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_others)
public class BeiJingFragment extends BaseFragment {
    @BindView(R.id.tv_position)
    TextView tvPosition;

    @Override
    public void afterBindView() {
        tvPosition.setText("北京");
    }
}
