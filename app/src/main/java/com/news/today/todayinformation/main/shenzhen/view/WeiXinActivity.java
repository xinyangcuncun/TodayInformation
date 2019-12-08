package com.news.today.todayinformation.main.shenzhen.view;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;
import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseActivity;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.base.tools.FileUtils;

import butterknife.BindView;

/**
 * Created by anson on 2019-11-17.
 */
@ViewInject(mainlayoutid = R.layout.activity_weixin)
public class WeiXinActivity extends BaseActivity {

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.bt_js)
    Button bt_js;
    @Override
    public void afterBindView() {

        bt_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //解析JS脚本
                //使用 v8 引擎
                V8 v8 = V8.createV8Runtime();
                V8Object v8Object = new V8Object(v8);
                v8.add("wx", v8Object);
                WxTextView wxTextView = new WxTextView();
                v8Object.registerJavaMethod(wxTextView, "textView", "textView", new Class[]{String.class});
                //解析JS文件
                String jsContent = FileUtils.getAssetsContent(WeiXinActivity.this, "js/wx.js");
                //使用V8引擎去 执行js脚本
                v8.executeVoidScript(jsContent);
            }
        });
    }

    public class WxTextView{
        //c++ 调用 Java 方法，都是通过反射的形式
        public void textView(String text) {
            TextView textView = new TextView(WeiXinActivity.this);
            textView.setText(text);
            ll_content.addView(textView);
        }
    }
}
