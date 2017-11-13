package com.e_at.super_order.mvp.presenter.impl;


import com.e_at.super_order.mvp.entity.AdListEntity;
import com.e_at.super_order.mvp.model.SplashModel;
import com.e_at.super_order.mvp.model.impl.SplashModelImpl;
import com.e_at.super_order.mvp.presenter.SplashPresenter;
import com.e_at.super_order.mvp.view.SplashView;

public class SplashPresenterImpl implements SplashPresenter {
    private SplashView mSplashView;
    private SplashModel mSplashModel;

    public SplashPresenterImpl(SplashView splashView) {
        mSplashView = splashView;
        this.mSplashModel = new SplashModelImpl();
    }

    @Override
    public void getAds() {
        mSplashModel.getAds(new SplashModel.onGetAdsListener() {
            @Override
            public void onSuccess(AdListEntity data) {
                mSplashView.jumpToAdPage(data);
            }

            @Override
            public void onFailed(String message) {
                mSplashView.jumpOverAuto(1500);
            }
        });
    }

    @Override
    public void jumpOverAuto(int delay) {
        mSplashView.jumpOverAuto(delay);
    }

    @Override
    public void attachView(SplashView view) {
        this.mSplashView = view;
    }

    @Override
    public void detachView() {
        this.mSplashView = null;
        ((SplashModelImpl) mSplashModel).unSubscribe();
    }
}
