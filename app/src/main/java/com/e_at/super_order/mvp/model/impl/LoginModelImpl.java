package com.e_at.super_order.mvp.model.impl;

import android.os.Build;

import com.e_at.super_order.application.OrderApplication;
import com.e_at.super_order.http.ApiCallBack;
import com.e_at.super_order.http.ParamsUtil;
import com.e_at.super_order.http.SubscriberCallBack;
import com.e_at.super_order.mvp.entity.BaseEntity;
import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.entity.PromotionEntity;
import com.e_at.super_order.mvp.model.LoginModel;
import com.e_at.super_order.utils.Constants;
import com.e_at.eatlibrary.utils.DeviceUtil;
import com.e_at.eatlibrary.utils.GeneralUtil;
import com.e_at.super_order.utils.SpConfigUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginModelImpl
 *
 * @author leo
 * @desc 登录model实现类
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public class LoginModelImpl extends BaseModelImpl implements LoginModel {

    @Override
    public void login(String phone, String code, final OnResponseListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("vCode", code);
        params.put("mac", DeviceUtil.getMACAddress(OrderApplication.getInstance()));
        params.put("appCategory", Constants.STR_APP_CATEGORY);

        params.put("appVersion", GeneralUtil.getVersionName(OrderApplication.getInstance()));//app版本号
        params.put("phoneModel", Build.MODEL);//手机型号
        params.put("sysVersion", Build.VERSION.RELEASE);//android系统版本
        params.put("sysSoftVersion", android.os.Build.DISPLAY);//版本显示
        params.put("deviceSn", DeviceUtil.getDeviceId(OrderApplication.getInstance()));//设备id

        addSubscriber(OrderApplication.sApiStores.login(ParamsUtil.packageParams(params)),
                new SubscriberCallBack(new ApiCallBack<LoginEntity>() {

                    @Override
                    public void onSuccess(LoginEntity data) {
                        listener.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String message) {
                        listener.onFailed(message);
                    }

                    @Override
                    public void onComplete() {
                        listener.dismissLoading();
                    }
                }));

    }

    @Override
    public void getShareLogInfo(String phone, String platformMemberId, final OnResponseListener listener) {

        Map<String, String> params = new HashMap<>();
        params.put("groupId", "1001");
        params.put("phone", SpConfigUtil.getPhone(OrderApplication.getInstance()));
        params.put("terminal", "1");
        params.put("platformMemberId", String.valueOf(SpConfigUtil.getPlatFormMemberId(OrderApplication.getInstance())));
        params.put("fromType", "7");
        addSubscriber(OrderApplication.sApiStores.getShareLogInfo(ParamsUtil.packageParams(params)),
                new SubscriberCallBack(new ApiCallBack<PromotionEntity>() {
                    @Override
                    public void onSuccess(PromotionEntity data) {
                        listener.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String message) {
                        listener.onFailed(message);
                    }

                    @Override
                    public void onComplete() {
                        listener.dismissLoading();
                    }
                }));
        
    }

    @Override
    public void getSMSCode(String phone, final onSMSListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        addSubscriber(OrderApplication.sApiStores.getSMSCode(ParamsUtil.packageParams(params)),
                new SubscriberCallBack(new ApiCallBack<BaseEntity>() {
                    @Override
                    public void onSuccess(BaseEntity data) {
                        listener.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String message) {
                        listener.onFailed(message);
                    }

                    @Override
                    public void onComplete() {
                        listener.dismissDialog();
                    }
                }));
    }

    @Override
    public void getVoiceCode(String phone, final onVoiceCodeListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        addSubscriber(OrderApplication.sApiStores.getVoiceCode(ParamsUtil.packageParams(params)),
                new SubscriberCallBack(new ApiCallBack<BaseEntity>() {
                    @Override
                    public void onSuccess(BaseEntity data) {
                        listener.onSuccess(data);
                    }

                    @Override
                    public void onFailure(String message) {
                        listener.onFailed(message);
                    }

                    @Override
                    public void onComplete() {
                        listener.dismissDialog();
                    }
                }));
    }
}
