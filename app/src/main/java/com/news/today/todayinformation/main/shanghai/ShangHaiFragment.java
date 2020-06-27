package com.news.today.todayinformation.main.shanghai;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.base.tools.AnimationUtil;
import com.news.today.todayinformation.base.tools.DoubleClickListener;
import com.news.today.todayinformation.main.shanghai.adapter.ShanghaiAdapter2;
import com.news.today.todayinformation.main.shanghai.lf.IPlayerServiceContract;
import com.news.today.todayinformation.main.shanghai.presenter.PlayerServicePresenter;
import com.web.god.plugin.GlideApp;
import com.web.god.plugin.TodayNewsImage;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment implements IPlayerServiceContract.Iview{

    IPlayerServiceContract.IPresenter mPresenter = new PlayerServicePresenter(this);

    @BindView(R.id.tv_shanghai_welcome)
    TextView tvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout shanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayot)
    AppBarLayout shanghaiAppBarlayot;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_marquee_title)
    TextView mTvTitle;
    @BindView(R.id.shanghai_iv_bg)
    ImageView mIvBg;
    private boolean mIsPlaying;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void afterBindView() {
        initView();
        initRecyclerView();
        initListener();
    }

    private void initView() {
//        RequestOptions cropOptions = new RequestOptions().centerCrop();
//        Glide.with(this).
//                load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590334586187&di=f47a809e125d25a733d71ca4e7e0c424&imgtype=0&src=http%3A%2F%2Ffile.youboy.com%2Fa%2F105%2F81%2F6%2F2%2F11099982s.jpg").
//                fitCenter().
//                placeholder(R.mipmap.shanghai).
//                apply(cropOptions).
//                into(mIvBg);
        TodayNewsImage todayNewsImage = new TodayNewsImage();
        todayNewsImage.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590334586187&di=f47a809e125d25a733d71ca4e7e0c424&imgtype=0&src=http%3A%2F%2Ffile.youboy.com%2Fa%2F105%2F81%2F6%2F2%2F11099982s.jpg");
        GlideApp.with(this).
                load(todayNewsImage).todayNews().
                into(mIvBg);
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
                Log.e("shanghaiAppBarlayot", "verticalOffset = " + verticalOffset + "appBarLayout = " + appBarLayout.getMeasuredHeight());
                if (-verticalOffset < appBarLayout.getMeasuredHeight() / 2) {
                    tvShanghaiWelcome.setVisibility(View.INVISIBLE);
                    mTvTitle.setVisibility(View.INVISIBLE);
                } else {
                    tvShanghaiWelcome.setVisibility(View.VISIBLE);
                    if (mIsPlaying) {
                        mTvTitle.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        tvShanghaiWelcome.setOnClickListener(new DoubleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTitle.clearAnimation();
                tvShanghaiWelcome.clearAnimation();
                if (mIsPlaying) {
                    //关闭音视频动画
                    mTvTitle.setVisibility(View.GONE);
                    AnimationUtil.startTranslationXAnim(tvShanghaiWelcome,tvShanghaiWelcome.getTranslationX(),tvShanghaiWelcome.getTranslationX() + 150,null);
                    AnimationUtil.startTranslationXAnim(mTvTitle,mTvTitle.getTranslationX(),mTvTitle.getTranslationX() + 150,null);
                    mPresenter.playOrPaused();

                } else {
                    //播放音视频动画
                    AnimationUtil.startTranslationXAnim(tvShanghaiWelcome,tvShanghaiWelcome.getTranslationX(),tvShanghaiWelcome.getTranslationX() - 150,null);
                    AnimationUtil.startTranslationXAnim(mTvTitle, mTvTitle.getTranslationX(), mTvTitle.getTranslationX() - 150, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mTvTitle.setVisibility(View.VISIBLE);
                            //启动Service 去 播放后台音乐
                            mPresenter.bindService(mContext);
                        }
                    });
                }
                mIsPlaying = !mIsPlaying;
            }
        }));
    }
}
