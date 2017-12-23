package com.common.liba.app;

import android.app.Activity;

import com.common.core.base.app.CoreApplication;
import com.common.core.base.app.IApplicationlike;
import com.common.core.base.injector.DispatchingCoreAndroidInjector;
import com.common.liba.injector.component.DaggerModuleAComponent;
import com.common.liba.injector.component.ModuleAComponent;

import javax.inject.Inject;

/**
 * user: zhangjianfeng
 * date: 23/12/2017
 * version: 7.3
 */

public class ModuleAApplication implements IApplicationlike {

    private static ModuleAApplication instance;

    private ModuleAComponent mModuleAComponent;

    @Inject
    DispatchingCoreAndroidInjector<Activity> mActivityInjector;

    @Override
    public void onCreate() {
        instance = this;
        setAppGraph();
    }

    public void setAppGraph() {
        mModuleAComponent = DaggerModuleAComponent.builder()
                .coreComponent(CoreApplication.getInstance().getDaggerDelegate().getComponent())
                .build();//.inject(this);
        mModuleAComponent.inject(this);
        CoreApplication.getInstance().getDaggerDelegate().addActivityInjector(mActivityInjector);
    }

    public static ModuleAApplication getInstance() {
        return instance;
    }

    public ModuleAComponent getmMedComponent() {
        return mModuleAComponent;
    }

    @Override
    public void onStop() {

    }
}
