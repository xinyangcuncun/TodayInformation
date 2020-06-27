package com.web.god.jetpack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.god.jetpack.lifeCircle.LifeCircleObserver;

/**
 * Created by anson on 2020-06-27.
 */
public class LifeCircleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getLifecycle().addObserver(new LifeCircleObserver());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecircle);
    }
}
