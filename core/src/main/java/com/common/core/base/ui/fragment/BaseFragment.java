package com.common.core.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.common.core.R;


public class BaseFragment extends DaggerBaseFragment {
    private LinearLayout mRootLayout;
    private Toolbar mToolbar;
    private View mViewLine;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View subView = onCreateSubView(inflater,mRootLayout,savedInstanceState);
        if(subView == null){
            Toast.makeText(getContext(),"Current fragment without view! Please check it", Toast.LENGTH_SHORT).show();
            return super.onCreateView(inflater,container,savedInstanceState);
        } else {
            View view = inflater.inflate(R.layout.core_layout_toolbar_base, container, false);
            mRootLayout = (LinearLayout) view.findViewById(R.id.root_layout);
            mRootLayout.addView(subView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar(view);
    }

    private void initToolbar(View view) {
        mViewLine = view.findViewById(R.id.root_diveder);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if(mToolbar != null) {
//            if(getActivity() instanceof AppCompatActivity) {
//                ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
//            }
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNavigationOnClick();
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    public View onCreateSubView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return  null;
    }


    public void setFragmentVisible(boolean visible){

    }

    public void onNavigationOnClick() {
        getActivity().finish();
    }

    public View getViewLine() {
        return mViewLine;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected void setTitle(int resId) {
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


}
