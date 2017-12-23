package com.common.component.injector.modules;

import com.common.component.main.MainFragment;
import com.common.core.base.injector.FragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */
@Module
public abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();


}
