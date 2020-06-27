package com.news.today.todayinformation.main.hangzhou.design;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by anson on 2019/3/10.
 */
public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView>{

    public BottomShowBehavior(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    // 这个方法的回调时机 ： 即将发生嵌套滚动时 nestedScrollAxes  用于判断滑动的方向
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    //发生嵌套滚动的时候 回调
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //向下滑动
        if (dyConsumed + dyUnconsumed > 0) {
            //隐藏child
            if (child.getVisibility() == View.VISIBLE) {
                BottomAnim.hide(child);
            }
        //向上滑动
        } else {
            //展示child
            if (child.getVisibility() != View.VISIBLE) {
                BottomAnim.show(child);
            }
        }
    }
}
