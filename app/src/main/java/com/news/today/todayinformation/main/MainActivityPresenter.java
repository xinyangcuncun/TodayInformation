package com.news.today.todayinformation.main;


import android.support.v4.app.Fragment;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.main.shanghai.BeiJingFragment;
import com.news.today.todayinformation.main.shanghai.HangZhowFragment;
import com.news.today.todayinformation.main.shanghai.ShangHaiFragment;
import com.news.today.todayinformation.main.shanghai.ShenZhenFragment;
import com.news.today.todayinformation.mvp.base.BaseMvpPresenter;

/**
 * Created by anson on 2018/11/11.
 */
public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter{

    // 当前Fragment的 角标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];
    private int mCurrentCheckedId;

    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }

    @Override
    protected IMainActivityContract.Iview getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0 ;
        replaceFragment(mCurrentFragmentIndex);
    }

    //切换Fragment的 方法
    private void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i < mFragments.length; i++) {
            if (mCurrentFragmentIndex != i) {
                if (mFragments[i] != null) {
                    hideFragment(mFragments[i]);
                }
            }
        }

        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null) {
            addAndShowFragment(mFragment);
            setCurChecked(mCurrentFragmentIndex);
        } else {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurChecked(mCurrentFragmentIndex);
        }

    }

    // 记录当前 角标
    private void setCurChecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex) {
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_nav_home_beijing;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_nav_car_source_shenzhen;
                break;
        }
    }

    //创建 当前 Fragment
    private void newCurrentFragment(int mCurrentFragmentIndex) {
         Fragment fragment = null;
        switch (mCurrentFragmentIndex) {
            case 0:
                fragment = new ShangHaiFragment();
                break;
            case 1:
                fragment = new HangZhowFragment();
                break;
            case 2:
                fragment = new BeiJingFragment();
                break;
            case 3:
                fragment = new ShenZhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    // 显示 Fragment
    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()) {
            getView().showFragment(mFragment);
        } else {
            getView().addFragment(mFragment);
        }
    }

    // 隐藏Fragment
    private void hideFragment(Fragment mFragment) {
        if (mFragment != null && mFragment.isVisible()) {
            getView().hideFragment(mFragment);
        }
    }
}
