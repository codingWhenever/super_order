package com.e_at.super_order.http;

import android.app.Activity;
import android.content.Intent;

import com.e_at.super_order.application.OrderApplication;
import com.e_at.super_order.mvp.ui.activity.LoginActivity;
import com.e_at.super_order.mvp.ui.activity.MainActivity;
import com.e_at.eatlibrary.utils.ToastUtil;

/**
 * ApiCallBack
 *
 * @author leo
 * @desc
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public abstract class ApiCallBack<T> implements ApiBaseCallBack<T> {


    @Override
    public void onAppUpdate(T data) {
        //版本更新
        OrderApplication.getInstance().updateApp();
    }

    @Override
    public void onBadToken() {
        //无效token
        Activity curActivity = OrderApplication.getInstance().curActivity;
        ToastUtil.showToast(curActivity, "登录超时，请重新登录");
        if (!(curActivity instanceof MainActivity)) {
            if (!(curActivity instanceof LoginActivity)) {

                Intent intent = new Intent(curActivity, LoginActivity.class);
                curActivity.startActivity(intent);
                curActivity.finish();
            } else {
                Intent intent = new Intent(curActivity, MainActivity.class);
                intent.putExtra("reLogin", true);
                curActivity.startActivity(intent);
            }
        } else {
            curActivity.finish();
            Intent intent = new Intent(curActivity, LoginActivity.class);
            curActivity.startActivity(intent);
        }
    }
}
