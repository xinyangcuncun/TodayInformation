package com.web.god.jetpack.mvvm;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于MVVM 框架中的协议
 */
@Retention(RUNTIME)  //运行时 注解
@Target(TYPE) // 类 接口 注解
// id 要和 viewModelClass 一一对应
public @interface MvvmViewInject {
    int mainlayoutid()  default  -1;
    int [] viewModelId();
    Class<? extends BaseMvvmModel> [] viewModelClass();
}
