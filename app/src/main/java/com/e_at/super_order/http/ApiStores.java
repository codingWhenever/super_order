package com.e_at.super_order.http;


import com.e_at.super_order.mvp.entity.LoginEntity;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface ApiStores {
    //登录

    @FormUrlEncoded
    @POST(Api.login)
    Observable<LoginEntity> login(@FieldMap Map<String, String> params);
}
