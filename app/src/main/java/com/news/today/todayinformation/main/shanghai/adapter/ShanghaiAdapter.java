package com.news.today.todayinformation.main.shanghai.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.news.today.todayinformation.R;

import java.util.ArrayList;

/**
 * Created by anson on 2018/12/3.
 */
public class ShanghaiAdapter extends RecyclerView.Adapter{

    private final ArrayList<String> mData;

    public ShanghaiAdapter(ArrayList<String> data) {
        mData = data;
    }

    //  创建View 然后进行缓存
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(inflate);
        return viewHolder;
    }

    // 绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ShanghaiViewHolder)holder).mTv.setText(mData.get(position));
    }

    // 条目的数量
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 缓存View  内存友好设计
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        public TextView mTv;

        public ShanghaiViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
        }
    }

}
