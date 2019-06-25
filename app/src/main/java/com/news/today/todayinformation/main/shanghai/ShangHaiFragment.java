package com.news.today.todayinformation.main.shanghai;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.base.view.FocusableTextView;
import com.news.today.todayinformation.main.shanghai.adapter.ShanghaiAdapter;
import com.news.today.todayinformation.main.shanghai.adapter.ShanghaiAdapter2;
import com.news.today.todayinformation.main.shanghai.dto.ShanghaiDataManager;
import com.news.today.todayinformation.main.shanghai.lf.IPlayerContract;
import com.news.today.todayinformation.main.shanghai.presenter.PlayerPresenter;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment implements IPlayerContract.Iview{

    private IPlayerContract.IPresenter mPlayerPresenter = new PlayerPresenter(this);

    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout shanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayot)
    AppBarLayout shanghaiAppBarlayot;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.title)
    FocusableTextView title;

    private boolean mIsPlaying = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void afterBindView() {
        initRecyclerView();
        initListener();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        mRecyclerView.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(),false));
        mRecyclerView.setAdapter(new ShanghaiAdapter2());
    }

    private void initListener() {
        shanghaiAppBarlayot.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                    tvShanghaiWelcome.setVisibility(View.GONE);
                } else {
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                }
            }
        });

        tvShanghaiWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                tvShanghaiWelcome.clearAnimation();
                title.clearAnimation();
                if (mIsPlaying) {
                    title.setVisibility(View.GONE);
                    ObjectAnimator handScale = ObjectAnimator.ofFloat(tvShanghaiWelcome, "translationX", tvShanghaiWelcome.getTranslationX(), tvShanghaiWelcome.getTranslationX() + 200);
                    handScale.setDuration(2500);
                    handScale.start();


                    ObjectAnimator handScale1 = ObjectAnimator.ofFloat(title, "translationX", title.getTranslationX(), title.getTranslationX() + 200);
                    handScale1.setDuration(2500);
                    handScale1.start();

                } else {
                    ObjectAnimator handScale = ObjectAnimator.ofFloat(tvShanghaiWelcome, "translationX", tvShanghaiWelcome.getTranslationX(), tvShanghaiWelcome.getTranslationX() - 200);
                    handScale.setDuration(2500);
                    handScale.start();


                    ObjectAnimator handScale1 = ObjectAnimator.ofFloat(title, "translationX", title.getTranslationX(), title.getTranslationX() - 200);
                    handScale1.setDuration(2500);
                    handScale1.start();
                    handScale1.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            title.setVisibility(View.VISIBLE);
                            //开启服务
                            mPlayerPresenter.bindPlayerService(mContext);

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                }
                mIsPlaying = !mIsPlaying;

            }
        });
    }
}
