package com.e_at.super_order.mvp.model;


import com.e_at.super_order.mvp.entity.AdListEntity;

public interface SplashModel extends BaseModel {
    void getAds(onGetAdsListener listener);


    interface onGetAdsListener {
        void onSuccess(AdListEntity data);

        void onFailed(String message);

    }
}
