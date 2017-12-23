package com.common.component.injector.component;

import com.common.component.app.MainApplication;
import com.common.component.injector.modules.ActivityModule;
import com.common.component.injector.modules.FragmentModule;
import com.common.core.base.injector.AppScope;
import com.common.core.base.injector.component.CoreComponent;

import dagger.Component;

/**
 * Created by zhangjianfeng on 2017/6/16.
 */
@AppScope
@Component(
        dependencies = CoreComponent.class,
        modules = {ActivityModule.class, FragmentModule.class}
       )
public interface AppComponent {

    void inject(MainApplication mainApplication);

}
