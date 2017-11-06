package com.e_at.super_order.mvp.view;


public interface LoginView extends BaseView {
    void loginSuccess(String message);

    void loginFailed(String message);
}
