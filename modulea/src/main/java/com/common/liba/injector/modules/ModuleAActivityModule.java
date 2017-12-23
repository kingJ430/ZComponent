package com.common.liba.injector.modules;

import com.common.core.base.injector.ActivityScope;
import com.common.liba.main.ModleATestActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */
@Module
public abstract class ModuleAActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract ModleATestActivity contributeModleATestActivity();


}
