package com.common.component.app;

import android.app.Activity;
import android.app.Service;

import com.common.component.injector.component.AppComponent;
import com.common.component.injector.component.DaggerAppComponent;
import com.common.core.base.app.CoreApplication;
import com.common.core.base.injector.DispatchingCoreAndroidInjector;

import javax.inject.Inject;

/**
 * user: zhangjianfeng
 * date: 23/12/2017
 * version: 7.3
 */

public class MainApplication extends CoreApplication {

    @Inject
    DispatchingCoreAndroidInjector<Activity> mActivityInjector;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setAppGraph();
    }

    public void setAppGraph() {
        mAppComponent = DaggerAppComponent.builder()
                .coreComponent(mDaggerDelegate.getComponent())
                .build();//.inject(this);
        mAppComponent.inject(this);
        mDaggerDelegate.addActivityInjector(mActivityInjector);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
