package com.news.today.todayinformation.main.hangzhou;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_others)
public class HangZhowFragment extends BaseFragment{
    @BindView(R.id.tv_position)
    TextView tvPosition;

    @Override
    public void afterBindView() {
        SpannableString spannableString =  new SpannableString("杭州");
        UnderlineSpan mUnderlineSpan = new UnderlineSpan();
        spannableString.setSpan(mUnderlineSpan,0,spannableString.length(), Spanned.SPAN_USER);
        tvPosition.setText(spannableString);

    }
}
