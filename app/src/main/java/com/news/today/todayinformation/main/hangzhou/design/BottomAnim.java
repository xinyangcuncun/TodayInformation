package com.news.today.todayinformation.main.hangzhou.design;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.news.today.todayinformation.R;

/**
 * Created by anson on 2019/3/11.
 */
public class BottomAnim {

    public static void show(View show) {
        //展示的动画
        show.clearAnimation();
        Animation animationShow = AnimationUtils.loadAnimation(show.getContext(), R.anim.main_tab_translate_show);
        show.startAnimation(animationShow);
        show.setVisibility(View.VISIBLE);
    }

    public static void hide(View gone) {
        //消失的动画
        gone.clearAnimation(); //清楚自身动画
        Animation animationGone = AnimationUtils.loadAnimation(gone.getContext(), R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.INVISIBLE);
    }
}
