package com.e_at.super_order.mvp.view;


import com.e_at.super_order.mvp.entity.LoginEntity;

public interface LoginView extends BaseView {
    void loginSuccess(LoginEntity data);

    void loginFailed(String message);

    void enableLogin();

    void disabledLogin();
}
