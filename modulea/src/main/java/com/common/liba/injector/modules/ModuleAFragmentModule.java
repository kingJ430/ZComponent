package com.common.liba.injector.modules;

import com.common.core.base.injector.FragmentScope;
import com.common.liba.main.ModleATestFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */
@Module
public abstract class ModuleAFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract ModleATestFragment contributeModleATestFragment();


}
