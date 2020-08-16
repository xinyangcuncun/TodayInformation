package com.news.today.todayinformation.main.shenzhen;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.shenzhen.view.MvvmActivity;
import com.news.today.todayinformation.main.shenzhen.view.OpenGlActivity;
import com.news.today.todayinformation.main.shenzhen.view.WebViewActivity;
import com.news.today.todayinformation.main.shenzhen.view.WeiXinActivity;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_shenzhen)
public class ShenZhenFragment extends BaseFragment{

    @BindView(R.id.bt_open_gl)
    Button openGL;

    @BindView(R.id.bt_webview)
    Button webView;

    @BindView(R.id.bt_wx)
    Button weixin;

    @BindView(R.id.bt_jetpack)
    Button jetPack;

    @Override
    public void afterBindView() {
        openGL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OpenGlActivity.class));
            }
        });

        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WebViewActivity.class));
            }
        });

        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WeiXinActivity.class));
            }
        });

        jetPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MvvmActivity.class));
            }
        });

    }
}
