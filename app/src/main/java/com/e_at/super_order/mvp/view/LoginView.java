package com.e_at.super_order.mvp.view;


import android.view.View;

import com.e_at.super_order.mvp.entity.BaseEntity;
import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.entity.PromotionEntity;

public interface LoginView extends BaseView {
    void loginSuccess(LoginEntity data);

    void loginFailed(String message);

    void enableLogin();

    void disabledLogin();


    void getShareInfoSuccess(PromotionEntity data);

    void getInfoFailed(String message);

    void getSMSCodeSuccess(BaseEntity baseEntity);

    void getSMSCodeFailed(String message);


    void codeCounter(View view);


    void getVoiceCodeSuccess(BaseEntity data);

    void getVoiceCodeFailed(String message);
    
}
