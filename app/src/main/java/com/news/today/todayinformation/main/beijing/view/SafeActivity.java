package com.news.today.todayinformation.main.beijing.view;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.beijing.util.MD5;

import butterknife.BindView;

/**
 * Created by anson on 2019-11-17.
 */
@ViewInject(mainlayoutid = R.layout.activity_safe)
public class SafeActivity extends BaseActivity {

    @BindView(R.id.tv_md5)
    TextView mTvMd5;

    @BindView(R.id.tv_sp)
    TextView mTvSp;
    @Override
    public void afterBindView() {

        mTvMd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvMd5.setText("加密后 = " + MD5.md5Decode32("todayInformation"));
            }
        });

        mTvSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putInt("password", 123456);
                edit.apply();
                mTvSp.setText("密码存储完毕");
            }
        });

    }
}
