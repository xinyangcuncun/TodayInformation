package com.news.today.todayinformation.main.shenzhen.view;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;

import butterknife.BindView;

/**
 * Created by anson on 2019-11-17.
 */
@ViewInject(mainlayoutid = R.layout.activity_webview)
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView web_view;
    @Override
    public void afterBindView() {
        web_view.setWebViewClient(new WebViewClient());
        web_view.loadUrl("http://www.baidu.com");
    }
}
