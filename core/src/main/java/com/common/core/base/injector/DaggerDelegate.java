package com.common.core.base.injector;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.common.core.base.injector.component.CoreComponent;
import com.common.core.base.injector.component.DaggerCoreComponent;
import com.common.core.base.injector.modules.DaggerModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;


public class DaggerDelegate  implements HasActivityInjector {

    private CoreComponent mComponent;
    private final Application mApplication;

    @Inject
    DispatchingCoreAndroidInjector<Activity> mActivityInjector;

    public DaggerDelegate(Application application) {
        mApplication = application;
    }

    public void onCreate() {

        //顶级依赖注入
        mComponent = DaggerCoreComponent.builder()
                .daggerModule(new DaggerModule(mApplication))
                .build();
        mComponent.inject(this);

    }


    public CoreComponent getComponent() {
        return mComponent;
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    public void addActivityInjector(DispatchingCoreAndroidInjector<Activity> activityInjector) {
        if(mActivityInjector != null && activityInjector != null) {
            mActivityInjector.addAllMap(activityInjector.getInjectorFactories());
        }
    }


}
