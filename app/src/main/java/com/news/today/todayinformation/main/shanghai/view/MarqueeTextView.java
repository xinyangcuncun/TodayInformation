package com.news.today.todayinformation.main.shanghai.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by anson on 2019/6/23.
 */
@SuppressLint("AppCompatCustomView")
public class MarqueeTextView extends TextView {


    public MarqueeTextView(Context context) {
        super(context);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
        }
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
}
