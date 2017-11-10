package com.e_at.super_order.application;

import android.app.Activity;
import android.app.Application;

import com.e_at.super_order.http.ApiStores;
import com.e_at.super_order.http.RetrofitClient;
import com.e_at.super_order.observer.OperationObserver;


public class OrderApplication extends Application {
    private static OrderApplication sOrderApplication;
    public Activity curActivity;
    public static ApiStores sApiStores;

    @Override
    public void onCreate() {
        super.onCreate();
        sOrderApplication = this;
        initAMap();
        initJPush();
        mOperationObserver = new OperationObserver();
        sApiStores = RetrofitClient.initRetrofit().create(ApiStores.class);
    }

    private void initJPush() {

    }

    private void initAMap() {

    }

    private OperationObserver mOperationObserver;

    public OperationObserver getOperationObserver() {
        return mOperationObserver;
    }

    public static OrderApplication getInstance() {
        return sOrderApplication;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
