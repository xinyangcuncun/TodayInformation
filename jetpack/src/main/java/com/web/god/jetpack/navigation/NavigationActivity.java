package com.web.god.jetpack.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.god.jetpack.R;

/**
 * Created by anson on 2020-07-12.
 */
public class NavigationActivity extends AppCompatActivity {

    //1\navigation 组件用于 单Acitivity架构 切换不同的fragment
    //2\不需要管理fragmentmanager
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }
}
