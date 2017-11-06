package com.e_at.super_order.application;

import android.app.Application;


public class OrderApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initAMap();
    }

    private void initAMap() {

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
