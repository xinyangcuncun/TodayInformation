package com.news.today.todayinformation.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.MainActivity;

import java.io.File;

import butterknife.BindView;

/**
 * Created by anson on 2018/4/24.
 */
@ViewInject(mainlayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview{

    @BindView(R.id.vv_play)
    FullScreenVideoView mVideoView;
//    @BindView(R.id.splash_vs)
//    ViewStub mViewStub;
    @BindView(R.id.tv_splash_timer)
    TextView mTvTimer;
    private ISplashActivityContract.IPresenter timerPresenter;

    @Override
    public void afterBindView() {
        initTimerPresenter();
        initListener();
        initVideo();
    }

    private void initTimerPresenter() {
        timerPresenter = new SplashTimerPresenter(this);
        timerPresenter.initTimer();
    }


    private void initVideo() {
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void initListener() {
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }


    @Override
    public void setTvTimer(String s) {
        /*if (TextUtils.equals(s, "跳过")) {
            View inflate = mViewStub.inflate();
            inflate.setVisibility(View.VISIBLE);
            inflate.findViewById(R.id.tv_splash_timer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            });
        }*/
        mTvTimer.setText(s);
    }

}
