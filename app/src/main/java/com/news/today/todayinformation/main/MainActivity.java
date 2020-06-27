package com.news.today.todayinformation.main;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.tools.MainConstantTool;

import butterknife.BindView;
import butterknife.OnClick;

@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview{

    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);

    @BindView(R.id.fac_main_home)
    FloatingActionButton facMainHome;
    @BindView(R.id.rb_main_shanghai)
    LottieAnimationView rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    LottieAnimationView rbMainHangzhou;
    @BindView(R.id.rg_main_top)
    LinearLayout rgMainTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.rb_main_nav_home_beijing)
    RadioButton rbMainNavHomeBeijing;
    @BindView(R.id.rb_main_nav_car_source_shenzhen)
    RadioButton rbMainNavCarSourceShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup rgMainBottom;

    private boolean isChangeTopOrBottom;


    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnima(rgMainBottom,rgMainTop);
        initCheckListener();
    }

    private void initCheckListener() {
        rbMainShanghai.playAnimation();
        rbMainShanghai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbMainShanghai.getId() == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
                rbMainShanghai.playAnimation();
                rbMainHangzhou.reverseAnimation();
            }
        });

        rbMainHangzhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbMainHangzhou.getId() == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
                rbMainHangzhou.playAnimation();
                rbMainShanghai.reverseAnimation();
            }
        });

        /*rgMainTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                switch (checkedId) {
                    case R.id.rb_main_shanghai:
                        mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
                        break;
                    case R.id.rb_main_hangzhou:
                        mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
                        break;
                }
            }*/
//        });

        rgMainBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mPresenter.getCurrentCheckedId()) {
                    return;
                }
                switch (checkedId) {
                    case R.id.rb_main_nav_home_beijing:
                        mPresenter.replaceFragment(MainConstantTool.BEIJING);
                        break;
                    case R.id.rb_main_nav_car_source_shenzhen:
                        mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
                        break;
                }
            }
        });
    }

    //初始化 Fragment
    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }


    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom) {
                    changeAnima(rgMainTop,rgMainBottom);
                    handleTopPosition();
                } else {
                    changeAnima(rgMainBottom,rgMainTop);
                    handleBottomPosition();
                }
                break;
        }
    }

    //北京 深圳
    private void handleBottomPosition() {
        if (mPresenter.getTopPosition() != 1) {
            mPresenter.replaceFragment(0);
            rbMainShanghai.pauseAnimation();
        }else {
            mPresenter.replaceFragment(1);
            rbMainHangzhou.playAnimation();
        }
    }

    //shanghai 杭州
    private void handleTopPosition() {
        if (mPresenter.getBottomPosition() != 3) {
            mPresenter.replaceFragment(2);
            rbMainNavHomeBeijing.setChecked(true);
        }else {
            mPresenter.replaceFragment(3);
            rbMainNavCarSourceShenzhen.setChecked(true);
        }
    }

    private void changeAnima(ViewGroup gone, ViewGroup show) {
        //消失的动画
        gone.clearAnimation(); //清楚自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);

        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(this, R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }

    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, mFragment).commit();
    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

}
