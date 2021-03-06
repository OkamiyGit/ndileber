package com.drcosu.ndileber.mvp.presenter;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.data.BaseDataSource;
import com.drcosu.ndileber.mvp.view.BaseView;
import com.drcosu.ndileber.utils.schedulers.BaseSchedulerProvider;
import com.drcosu.ndileber.utils.schedulers.SchedulerProvider;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by shidawei on 2016/9/24.
 */
public abstract class DileberRxPresenter<T1 extends BaseView,T2 extends BaseDataSource> implements BasePresenter,IRxPresenter{


    protected T1 mView;
    protected T2 mDataSource;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;
    @NonNull
    protected CompositeSubscription mSubscriptions;

    public DileberRxPresenter(@NonNull T1 view, @NonNull T2 mDataSource){
        this.mView = view;
        this.mDataSource = mDataSource;
        mSchedulerProvider = SchedulerProvider.getInstance();
        mSubscriptions = new CompositeSubscription();
        this.mView.setPresenter(this);
    }

    @Override
    public void onResume() {
        subscribe();
    }

    @Override
    public void onPause() {
        unsubscribe();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void remove(Subscription s) {
        mSubscriptions.remove(s);
    }

    @Override
    public void add(Subscription s) {
        mSubscriptions.add(s);
    }

    @Override
    public void addAll(Subscription... subscriptions) {
        mSubscriptions.addAll(subscriptions);
    }
}
