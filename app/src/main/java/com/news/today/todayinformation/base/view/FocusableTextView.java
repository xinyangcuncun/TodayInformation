package com.news.today.todayinformation.base.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;


public class FocusableTextView extends TextView {
    private static final String TAG = "FocusableTextView";

    public FocusableTextView(Context context) {
        this(context, null);
    }

    public FocusableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setMarqueeRepeatLimit(-1);//-1表示无限重复
        setSingleLine(true);
    }

    @Override
    public boolean isFocused() {

        return true;

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {

        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {

        if (hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
        }
    }
}
