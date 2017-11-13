package com.e_at.super_order.mvp.model.impl;


import com.e_at.super_order.application.OrderApplication;
import com.e_at.super_order.http.ApiCallBack;
import com.e_at.super_order.http.SubscriberCallBack;
import com.e_at.super_order.mvp.entity.AdListEntity;
import com.e_at.super_order.mvp.model.SplashModel;

public class SplashModelImpl extends BaseModelImpl implements SplashModel {
    @Override
    public void getAds(final onGetAdsListener listener) {
        addSubscriber(OrderApplication.sApiStores.getAds(),
                new SubscriberCallBack(new ApiCallBack<AdListEntity>() {
                    @Override
                    public void onSuccess(AdListEntity data) {
                        listener.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String message) {
                        listener.onFailed(message);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
