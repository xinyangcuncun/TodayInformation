package com.news.today.todayinformation.main.hangzhou;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.hangzhou.adpter.HangzhouViewPagerAdapter;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_hangzhou)
public class HangZhowFragment extends BaseFragment {


    @BindView(R.id.tl_tablayout)
    TabLayout tlTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @Override
    public void afterBindView() {
        tlTablayout.setupWithViewPager(vpViewpager);
        vpViewpager.setAdapter(new HangzhouViewPagerAdapter(getChildFragmentManager()));
    }

}
