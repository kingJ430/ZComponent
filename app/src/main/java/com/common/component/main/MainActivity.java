package com.common.component.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.common.component.R;
import com.common.core.base.ui.BaseBindPresenterActivity;

/**
 * user: zhangjianfeng
 * date: 23/12/2017
 * version: 7.3
 */
public class MainActivity extends BaseBindPresenterActivity<MainPresenter> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_layout);
    }

}
