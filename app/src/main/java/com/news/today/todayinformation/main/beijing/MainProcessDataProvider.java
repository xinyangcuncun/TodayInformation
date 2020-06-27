package com.news.today.todayinformation.main.beijing;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by anson on 2019/8/24.
 */
public class MainProcessDataProvider  extends ContentProvider{

    @Override
    public boolean onCreate() {
        return false;
    }

    //可以提供访问数据库的能力
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    //数据库的插入
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String processDec = ProcessDataTest.getIntance().getProcessDec();
        return Uri.parse(processDec);
    }

    //数据库的删除
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
