package com.common.liba.injector.component;

import android.test.UiThreadTest;

import com.common.core.base.injector.AppScope;
import com.common.core.base.injector.component.CoreComponent;
import com.common.liba.app.ModuleAApplication;
import com.common.liba.injector.modules.ModuleAActivityModule;
import com.common.liba.injector.modules.ModuleAFragmentModule;

import dagger.Component;
import dagger.Module;

/**
 * user: zhangjianfeng
 * date: 23/12/2017
 * version: 7.3
 */
@AppScope
@Component(dependencies = CoreComponent.class,
modules = {ModuleAActivityModule.class, ModuleAFragmentModule.class})
public interface ModuleAComponent {
    void inject(ModuleAApplication moduleAApplication);
}
