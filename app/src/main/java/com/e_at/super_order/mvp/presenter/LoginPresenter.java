package com.e_at.super_order.mvp.presenter;


import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {

    void login(LoginEntity user);
}
