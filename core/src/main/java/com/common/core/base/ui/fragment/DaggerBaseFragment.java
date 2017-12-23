package com.common.core.base.ui.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.common.core.base.injector.CoreAndroidInjection;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * user: zhangjianfeng
 * date: 12/10/2017
 * version: 7.3
 */

public class DaggerBaseFragment extends Fragment implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    @Override
    public void onAttach(Context context) {
        CoreAndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }
}
