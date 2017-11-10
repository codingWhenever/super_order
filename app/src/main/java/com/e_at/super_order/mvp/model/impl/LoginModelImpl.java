package com.e_at.super_order.mvp.model.impl;

import android.os.Build;

import com.e_at.super_order.application.OrderApplication;
import com.e_at.super_order.http.ApiCallBack;
import com.e_at.super_order.http.ParamsUtil;
import com.e_at.super_order.http.SubscriberCallBack;
import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.model.LoginModel;
import com.e_at.super_order.utils.Constants;
import com.e_at.super_order.utils.DeviceUtil;
import com.e_at.super_order.utils.GeneralUtil;

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
    public void login(String phone, String code, final OnLoginListener listener) {
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
                        listener.loginSuccess(data);
                    }

                    @Override
                    public void onFailure(String message) {
                        listener.loginFailed(message);
                    }

                    @Override
                    public void onComplete() {
                        listener.dismissLoading();
                    }
                }));

    }

    @Override
    public void getShareLogInfo(String phone, String platformMemberId) {

    }
}
