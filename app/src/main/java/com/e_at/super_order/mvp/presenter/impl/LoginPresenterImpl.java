package com.e_at.super_order.mvp.presenter.impl;


import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.model.LoginModel;
import com.e_at.super_order.mvp.model.impl.LoginModelImpl;
import com.e_at.super_order.mvp.presenter.LoginPresenter;
import com.e_at.super_order.mvp.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginModel = new LoginModelImpl();
    }

    @Override
    public void attachView(LoginView view) {
        this.mLoginView = view;
    }

    @Override
    public void detachView() {
        this.mLoginView = null;
        ((LoginModelImpl) this.mLoginModel).unSubscribe();
    }

    @Override
    public void login(String phone, String code) {
        mLoginView.showLoading(null);

        mLoginModel.login(phone, code, new LoginModel.OnLoginListener() {
            @Override
            public void loginSuccess(LoginEntity loginEntity) {
                mLoginView.loginSuccess(loginEntity);
            }

            @Override
            public void loginFailed(String message) {
                mLoginView.loginFailed(message);
            }

            @Override
            public void dismissLoading() {
                mLoginView.dismissDialog();
            }
        });
    }

    @Override
    public void getShareLogInfo(String phone, String platformMemberId) {

    }
}
