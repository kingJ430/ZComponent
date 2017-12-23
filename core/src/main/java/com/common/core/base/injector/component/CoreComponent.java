package com.common.core.base.injector.component;


import com.common.core.base.injector.DaggerDelegate;
import com.common.core.base.injector.modules.DaggerModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */
@Singleton
@Component(
        modules = {AndroidSupportInjectionModule.class,DaggerModule.class})
public interface CoreComponent {

    void inject(DaggerDelegate daggerDelegate);

}
