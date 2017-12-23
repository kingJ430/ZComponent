package com.common.core.base.mvp.presenter;


import com.common.core.base.mvp.mvpview.MvpView;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mMvpView);

    void detachView();
}
