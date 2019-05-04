package com.news.today.todayinformation.base;

import com.anson.abc.mvp.mvp.IMvpView;
import com.anson.abc.mvp.mvp.base.BaseMvpPresenter;
import com.anson.abc.task.LfTask;
import com.anson.abc.task.TaskHelper;

import today.information.mvp.MvpEmptyViewFactory;

/**
 * Created by anson on 2019/1/13. 集成mvp 及 网络请求 快捷方式
 */
public  class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T>{

    public BasePresenter(T view) {
        super(view);
    }

    public void submitTask(LfTask task) {
        TaskHelper.submitTask(task,task);
    }

    @Override
    protected T getEmptyView() {
        T t = null;
        Class superClassGenricType = GenericsUtils.getSuperClassGenricType(this.getClass(), 0);
        try {
            t = (T)MvpEmptyViewFactory.create(superClassGenricType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
