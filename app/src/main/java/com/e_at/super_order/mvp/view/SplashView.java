package com.e_at.super_order.mvp.view;


import com.e_at.super_order.mvp.entity.AdListEntity;

public interface SplashView extends BaseView {

    void jumpToAdPage(AdListEntity data);

    void jumpOverAuto(int delay);
}
