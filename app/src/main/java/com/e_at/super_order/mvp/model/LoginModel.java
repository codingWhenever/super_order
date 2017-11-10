package com.e_at.super_order.mvp.model;


import com.e_at.super_order.mvp.entity.LoginEntity;

/**
 * LoginModel
 *
 * @author leo
 * @desc 登录model
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public interface LoginModel extends BaseModel {

    void login(String phone, String code, OnLoginListener listener);

    void getShareLogInfo(String phone, String platformMemberId);

    interface OnLoginListener {
        void loginSuccess(LoginEntity loginEntity);

        void loginFailed(String message);

        void dismissLoading();
    }
}
