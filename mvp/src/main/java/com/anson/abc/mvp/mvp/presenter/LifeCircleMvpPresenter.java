package com.anson.abc.mvp.mvp.presenter;

import com.anson.abc.mvp.mvp.ILifeCircle;
import com.anson.abc.mvp.mvp.IMvpView;
import com.anson.abc.mvp.mvp.MvpControler;

import java.lang.ref.WeakReference;

/**
 * Created by anson on 2018/10/13.
 */
public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {

    protected WeakReference<T> weakReference;
    
    protected LifeCircleMvpPresenter() {
        super();
    }

    public LifeCircleMvpPresenter(IMvpView iMvpView) {
        super();
        attachView(iMvpView);
        MvpControler mvpControler = iMvpView.getMvpControler();
        mvpControler.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if (weakReference == null) {
            weakReference = new WeakReference(iMvpView);
        } else {
            T view = (T) weakReference.get();
            if (view != iMvpView) {
                weakReference = new WeakReference(iMvpView);
            }
        } 
    }

    @Override
    public void onDestroy() {
        weakReference = null; 
    }
    
    protected T getView() {
        T view = weakReference != null ? (T) weakReference.get() : null;
        if (view == null) {
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();

}
