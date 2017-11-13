package com.e_at.super_order.mvp.model;


import com.e_at.super_order.mvp.entity.BaseEntity;

/**
 * LoginModel
 *
 * @author leo
 * @desc 登录model
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public interface LoginModel extends BaseModel {

    void login(String phone, String code, OnResponseListener listener);

    void getShareLogInfo(String phone, String platformMemberId, OnResponseListener listener);

    void getSMSCode(String phone, onSMSListener listener);

    void getVoiceCode(String phone, onVoiceCodeListener listener);

    interface OnResponseListener {
        void onSuccess(BaseEntity loginEntity);

        void onFailed(String message);

        void dismissLoading();
    }

    interface onSMSListener {
        void onSuccess(BaseEntity baseEntity);

        void onFailed(String message);

        void dismissDialog();
    }

    interface onVoiceCodeListener {
        void onSuccess(BaseEntity baseEntity);

        void onFailed(String message);

        void dismissDialog();
    }
}
