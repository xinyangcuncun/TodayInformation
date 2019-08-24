package com.news.today.todayinformation.main.beijing;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.news.today.todayinformation.R;
import com.news.today.todayinformation.base.BaseFragment;
import com.news.today.todayinformation.base.ViewInject;
import com.news.today.todayinformation.main.shanghai.view.ShanghaiDetailActivity;

import butterknife.BindView;

/**
 * Created by anson on 2018/11/18.
 */
@ViewInject(mainlayoutid = R.layout.fragment_beijing)
public class BeiJingFragment extends BaseFragment {
    @BindView(R.id.bt_paly)
    Button tvClick;
//    private ProcessDataReceiver processDataReceiver;

    @Override
    public void afterBindView() {
        mContext.startService(new Intent(mContext,MainProcessService.class));
        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去上海
                ProcessDataTest.getIntance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(),tvClick);
            }
        });
//        processDataReceiver = new ProcessDataReceiver();
//        getActivity().registerReceiver(processDataReceiver, new IntentFilter("shanghai_get_process_data"));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.stopService(new Intent(mContext, MainProcessService.class));
//        getActivity().unregisterReceiver(processDataReceiver);
    }

//    private class ProcessDataReceiver  extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = ProcessDataTest.getIntance().getProcessDec();
//            Intent postIntent =  new Intent("beijing_post_process_data");
//            postIntent.putExtra("processDec", processDec);
//            getActivity().sendBroadcast(postIntent);
//        }
//    }
}
