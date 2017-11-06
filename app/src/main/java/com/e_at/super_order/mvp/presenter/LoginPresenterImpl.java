package com.e_at.super_order.mvp.presenter;


import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView mLoginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void attachView(LoginView view) {
        this.mLoginView = view;
    }

    @Override
    public void detachView() {
        this.mLoginView = null;
    }

    @Override
    public void login(LoginEntity user) {

    }
}
