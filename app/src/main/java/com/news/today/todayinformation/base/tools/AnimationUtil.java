package com.news.today.todayinformation.base.tools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by anson on 2019/6/23.
 */
public class AnimationUtil {

    /**
     * x轴方向的属性动画
     * @param taget
     * @param positionStart
     * @param positionEnd
     * @param listener
     */
    public static void startTranslationXAnim(View taget, float positionStart, float positionEnd, Animator.AnimatorListener listener) {
        ObjectAnimator titleAnim = ObjectAnimator.ofFloat(taget, "translationX", positionStart, positionEnd);
        titleAnim.setDuration(1000);
        titleAnim.start();
        if (listener != null) {
            titleAnim.addListener(listener);
        }
    }
}
