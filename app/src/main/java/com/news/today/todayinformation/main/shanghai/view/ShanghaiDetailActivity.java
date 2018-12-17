package com.news.today.todayinformation.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;

import butterknife.BindView;

/**
 * Created by anson on 2018/12/12.
 */
@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity {

    public static String mActivityOptionsCompat = "ShanghaiDetailActivity";
    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    @Override
    public void afterBindView() {
        initAnima();
    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ViewCompat.setTransitionName(ivShanghaiDetail,mActivityOptionsCompat);
            //开启转场动画
            startPostponedEnterTransition();
        }
    }

    /**
     * 用于 Android 5.0 系统的 界面转场动画  ：共享元素动画
     */
    public static void start_5_0(Activity activity,View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity,ShanghaiDetailActivity.class);
            Pair pair = new Pair(view,mActivityOptionsCompat);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity,intent,optionsCompat.toBundle());
        }
    }
}
