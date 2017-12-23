package com.common.component.injector.modules;

import com.common.component.main.MainActivity;
import com.common.core.base.injector.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */
@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();


}
