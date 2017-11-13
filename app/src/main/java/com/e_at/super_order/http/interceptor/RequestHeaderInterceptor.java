package com.e_at.super_order.http.interceptor;


import com.e_at.super_order.application.OrderApplication;
import com.e_at.eatlibrary.utils.GeneralUtil;
import com.e_at.super_order.utils.SpConfigUtil;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * RequestHeaderInterceptor
 *
 * @author leo
 * @desc 请求头部参数
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public class RequestHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("fromType", "7")
                .header("addVersion", GeneralUtil.getVersionName(OrderApplication.getInstance()))
                .header("token", SpConfigUtil.getToken(OrderApplication.getInstance()))
                .build();
        Logger.d(request.headers().toString());
        return chain.proceed(request);
    }
}
