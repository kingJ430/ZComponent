package com.common.core.base.mvp.presenter;


import com.common.core.base.mvp.mvpview.MvpView;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */


public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;


    @Override
    public void attachView(T pMvpView) {
        mMvpView = pMvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {

        return mMvpView;
    }


    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
