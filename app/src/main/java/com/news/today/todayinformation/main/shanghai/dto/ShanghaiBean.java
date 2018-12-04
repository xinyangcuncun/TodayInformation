package com.news.today.todayinformation.main.shanghai.dto;

import java.util.ArrayList;

/**
 * Created by anson on 2018/12/4.
 */
public class ShanghaiBean {
    private int mItemType = IShanghaiItemType.VERTICAL;
    private String mDec;
    private boolean isShowImg;
    private ArrayList<ShanghaiBean> data;


    public int getmItemType() {
        return mItemType;
    }

    public ShanghaiBean setmItemType(int mItemType) {
        this.mItemType = mItemType;
        return this;
    }

    public String getmDec() {
        return mDec;
    }

    public ShanghaiBean setmDec(String mDec) {
        this.mDec = mDec;
        return this;
    }

    public boolean isShowImg() {
        return isShowImg;
    }

    public ShanghaiBean setShowImg(boolean showImg) {
        isShowImg = showImg;
        return this;
    }

    public ArrayList<ShanghaiBean> getData() {
        return data;
    }

    public ShanghaiBean setData(ArrayList<ShanghaiBean> data) {
        this.data = data;
        return this;
    }

   public interface IShanghaiItemType{
       int VERTICAL = 0;
       int HORIZANTAL = 1;
   }

}
