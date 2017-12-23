package com.common.core.base.app;

import android.app.Application;

import com.common.core.base.injector.DaggerDelegate;

import dagger.android.HasActivityInjector;

/**
 * user: zhangjianfeng
 * date: 23/12/2017
 * version: 7.3
 */

public class CoreApplication extends Application {

    private static CoreApplication instance;

    protected DaggerDelegate mDaggerDelegate;

    public static CoreApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        setCoreGraph();
        super.onCreate();
        instance = this;
    }

    public void setCoreGraph() {
        mDaggerDelegate = new DaggerDelegate(this);
        mDaggerDelegate.onCreate();

    }

    public DaggerDelegate getDaggerDelegate() {
        return mDaggerDelegate;
    }

    public HasActivityInjector activityInjector() {
        return mDaggerDelegate;
    }
}
