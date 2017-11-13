package com.e_at.super_order.mvp.presenter;


import com.e_at.super_order.mvp.view.SplashView;

public interface SplashPresenter extends BasePresenter<SplashView> {
    void getAds();

    void jumpOverAuto(int delay);
}
