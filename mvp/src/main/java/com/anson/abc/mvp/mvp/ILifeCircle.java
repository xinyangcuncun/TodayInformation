package com.anson.abc.mvp.mvp;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by anson on 2018/10/13.
 */
public interface ILifeCircle {

    void onCreate(Bundle savedInstanceState, Intent intent,Bundle getArguments);

    void onActivityCreated(Bundle savedInstanceState, Intent intent,Bundle getArguments);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void destroyView();

    void onViewDestroy();

    void onNewIntent(Intent intent);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onSaveInstanceState(Bundle bundle);

    void attachView(IMvpView iMvpView);
}
