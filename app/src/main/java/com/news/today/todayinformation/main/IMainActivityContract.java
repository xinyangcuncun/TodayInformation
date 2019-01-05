package com.news.today.todayinformation.main;

import android.support.v4.app.Fragment;

import com.anson.abc.mvp.mvp.ILifeCircle;
import com.anson.abc.mvp.mvp.IMvpView;
import com.anson.abc.mvp.mvp.MvpControler;

/**
 * Created by anson on 2018/10/28.
 */
public interface IMainActivityContract {

    interface Iview extends IMvpView {

        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);

    }

    interface IPresenter extends ILifeCircle {

        void initHomeFragment();

        int getCurrentCheckedId();

        void replaceFragment(int mCurrentFragmentIndex);

        int getCurrentCheckedIndex();

        int getTopPosition();

        int getBottomPosition();
    }

    Iview emptyView = new Iview() {

        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };

}
