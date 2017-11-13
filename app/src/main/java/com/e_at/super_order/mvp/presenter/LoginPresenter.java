package com.e_at.super_order.mvp.presenter;


import com.e_at.super_order.mvp.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {

    void login(String phone, String code);

    void getShareLogInfo(String phone, String platformMemberId);

    void getSMSCode(String phone);

    void getVoiceCode(String phone);
}
