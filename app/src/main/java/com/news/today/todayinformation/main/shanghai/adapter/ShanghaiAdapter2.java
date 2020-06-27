package com.news.today.todayinformation.main.shanghai.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.today.todayinformation.R;

/**
 * Created by anson on 2018/12/3.
 */
public class ShanghaiAdapter2 extends RecyclerView.Adapter {


    public ShanghaiAdapter2() {

    }

    //  创建View 然后进行缓存
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_cardview, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(inflate);
        return viewHolder;
    }

    // 绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


    }

    // 条目的数量
    @Override
    public int getItemCount() {
        return 15;
    }


    // 缓存View  内存友好设计
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {


        public ShanghaiViewHolder(View itemView) {
            super(itemView);

        }
    }


}
