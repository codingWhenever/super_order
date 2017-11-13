package com.e_at.super_order.mvp.presenter.impl;


import com.e_at.super_order.mvp.entity.BaseEntity;
import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.entity.PromotionEntity;
import com.e_at.super_order.mvp.model.LoginModel;
import com.e_at.super_order.mvp.model.impl.LoginModelImpl;
import com.e_at.super_order.mvp.presenter.LoginPresenter;
import com.e_at.super_order.mvp.view.LoginView;

/**
 * LoginPresenterImpl
 *
 * @author leo
 * @desc 登录页面presenter
 * @date 2017/11/13
 * @email lei.lu@e-at.com
 */
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

        mLoginModel.login(phone, code, new LoginModel.OnResponseListener() {
            @Override
            public void onSuccess(BaseEntity loginEntity) {
                mLoginView.loginSuccess((LoginEntity) loginEntity);
            }

            @Override
            public void onFailed(String message) {
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
        mLoginModel.getShareLogInfo(phone, platformMemberId, new LoginModel.OnResponseListener() {
            @Override
            public void onSuccess(BaseEntity data) {
                mLoginView.getShareInfoSuccess((PromotionEntity) data);
            }

            @Override
            public void onFailed(String message) {
                mLoginView.getInfoFailed(message);
            }

            @Override
            public void dismissLoading() {
                mLoginView.dismissDialog();
            }
        });
    }

    @Override
    public void getSMSCode(String phone) {
        mLoginModel.getSMSCode(phone, new LoginModel.onSMSListener() {
            @Override
            public void onSuccess(BaseEntity baseEntity) {
                mLoginView.getSMSCodeSuccess(baseEntity);
            }

            @Override
            public void onFailed(String message) {
                mLoginView.getSMSCodeFailed(message);
            }

            @Override
            public void dismissDialog() {
                mLoginView.dismissDialog();
            }
        });
    }

    @Override
    public void getVoiceCode(String phone) {
        mLoginModel.getVoiceCode(phone, new LoginModel.onVoiceCodeListener() {
            @Override
            public void onSuccess(BaseEntity baseEntity) {
                mLoginView.getVoiceCodeSuccess(baseEntity);
            }

            @Override
            public void onFailed(String message) {
                mLoginView.getVoiceCodeFailed(message);
            }

            @Override
            public void dismissDialog() {
                mLoginView.dismissDialog();
            }
        });
    }
}
