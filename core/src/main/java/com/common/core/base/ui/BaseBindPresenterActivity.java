package com.common.core.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.common.core.base.mvp.mvpview.MvpView;
import com.common.core.base.mvp.presenter.BasePresenter;

import javax.inject.Inject;


/**
 * Created by zhangjianfeng on 2017/6/16.
 */

public abstract class BaseBindPresenterActivity<T extends BasePresenter> extends BaseActivity implements MvpView {


    @Inject
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != this.mPresenter) {
            this.mPresenter.attachView(this);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != this.mPresenter) {
            this.mPresenter.detachView();
        }
    }


}
