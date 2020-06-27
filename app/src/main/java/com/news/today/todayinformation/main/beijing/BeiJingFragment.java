package com.news.today.todayinformation.main.beijing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.beijing.view.SafeActivity;
import com.news.today.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_beijing)
public class BeiJingFragment extends BaseFragment {
    @BindView(R.id.bt_paly)
    Button tvClick;
    @BindView(R.id.perimision)
    Button btClick;
    @BindView(R.id.android_safe)
    Button btSafe;

    @Override
    public void afterBindView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mContext.startForegroundService(new Intent(mContext, MainProcessService.class));
        } else {
            mContext.startService(new Intent(mContext,MainProcessService.class));
        }
        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去上海
                ProcessDataTest.getIntance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(),tvClick);
            }
        });

        btClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int state = getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (state == PackageManager.PERMISSION_GRANTED) {
                        Log.e("BeiJingFragment", "权限已经申请");
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                        Log.e("BeiJingFragment", "权限没有申请");
                    }
                }
            }
        });

        btSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), SafeActivity.class));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("BeiJingFragment", grantResults[0] + " ");
    }
}
