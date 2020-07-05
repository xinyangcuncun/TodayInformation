package com.web.god.jetpack;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.web.god.jetpack.lifeCircle.LifeCircleViewModel;

/**
 * Created by anson on 2020-06-27.
 */
public class LifeCircleActivity extends AppCompatActivity {

    private TextView mTvContent;
    private LifeCircleViewModel lifeCircleViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        lifeCircleViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(LifeCircleViewModel.class);
        getLifecycle().addObserver(lifeCircleViewModel);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecircle);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        lifeCircleViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTvContent.setText(s);
            }
        });
    }

}
