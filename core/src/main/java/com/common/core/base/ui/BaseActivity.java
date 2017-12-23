package com.common.core.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.common.core.R;
import com.common.core.base.app.CoreApplication;

import dagger.android.HasActivityInjector;


public class BaseActivity extends DaggerBaseAppCompatActivity {

    private LinearLayout mRootLayout;
    private Toolbar mToolbar;
    private View mViewLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        super.setContentView(R.layout.core_layout_toolbar_base);
        initToolbar();
    }

    private void initToolbar() {
        mViewLine = (View)findViewById(R.id.root_diveder);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(mToolbar != null){
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationOnClick();
                    finish();
                }
            });
        }
    }


    public void onNavigationOnClick() {
    }

    public Toolbar getToolbar(){
        return mToolbar ;
    }

    public View getViewLine(){
        return mViewLine ;
    }

    public void setTitle(int resId) {
        if(null != mToolbar)
            mToolbar.setTitle(resId);
    }

    protected void setTitle(String title) {
        if(null != mToolbar)
            mToolbar.setTitle(title);
    }

    protected void hideNavigationIcon() {
        if(mToolbar != null)
            mToolbar.setNavigationIcon(null);
    }


    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        mRootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (mRootLayout == null) return;
        mRootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    @Override
    public HasActivityInjector getActivityInjector() {
        return CoreApplication.getInstance().activityInjector();
    }
}
