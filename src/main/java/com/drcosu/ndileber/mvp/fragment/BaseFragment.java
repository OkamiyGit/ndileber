package com.drcosu.ndileber.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drcosu.ndileber.tools.UUi;
import com.orhanobut.logger.Logger;


/**
 * Created by shidawei on 16/6/2.
 */
public abstract class BaseFragment extends Fragment{

    /**
     * 主要用于缓存view
     * 用 sparseArray 代替hashmap是个性能上不错的选择
     * SparseArray来代替HashMap了，但是要注意SparseArray中的key是int类型\
     */
    protected final SparseArray<View> mViews = new SparseArray<View>();

    public <T extends View> T getView(View mView,int id) {
        return UUi.getView(mView,mViews,id);
    }

    protected abstract int layoutViewId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(layoutViewId(), container, false);
        initView(view,savedInstanceState);
        return view;
    }

    protected abstract void initView(View view, Bundle savedInstanceState);


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示
            hidden();
            Logger.i("hidden");
        } else {// 重新显示到最前端中
            show();
            Logger.i("show");

        }
    }


    /**
     * fragment 显示时候调用的方法
     */
    protected abstract void show();

    /**
     * fragment 隐藏时候调用的方法
     */
    protected abstract void hidden();

}
