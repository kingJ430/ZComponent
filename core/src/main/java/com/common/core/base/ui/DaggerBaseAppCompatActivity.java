package com.common.core.base.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.common.core.base.injector.CoreAndroidInjection;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * user: zhangjianfeng
 * date: 12/10/2017
 * version: 7.3
 */

public abstract class DaggerBaseAppCompatActivity extends AppCompatActivity
        implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;
//    @Inject DispatchingCoreAndroidInjector<android.app.Fragment> frameworkFragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CoreAndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }

//    @Override
//    public AndroidInjector<android.app.Fragment> fragmentInjector() {
//        return frameworkFragmentInjector;
//    }

    public abstract HasActivityInjector getActivityInjector();
}
