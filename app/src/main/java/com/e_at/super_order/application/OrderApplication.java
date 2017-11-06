package com.e_at.super_order.application;

import android.app.Activity;
import android.app.Application;


public class OrderApplication extends Application {
    private static OrderApplication sOrderApplication;
    public Activity curActivity;
    @Override
    public void onCreate() {
        super.onCreate();
        sOrderApplication = this;
        initAMap();
    }

    private void initAMap() {

    }

    public static OrderApplication getInstance() {
        return sOrderApplication;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
