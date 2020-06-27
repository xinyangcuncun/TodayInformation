package com.anson.abc.http.annotation;

import androidx.annotation.IntDef;

import static com.anson.abc.http.annotation.RequestMethod.Get;
import static com.anson.abc.http.annotation.RequestMethod.Post;


/**
 * Created by anson on 2019/1/5.
 */
@IntDef({Get,Post})
public @interface RequestMethod {
    int Get = 1;
    int Post = 2;
}
