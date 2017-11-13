package com.e_at.super_order.http;


import com.e_at.super_order.mvp.entity.AdListEntity;
import com.e_at.super_order.mvp.entity.BaseEntity;
import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.entity.PromotionEntity;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * ApiStores
 *
 * @author leo
 * @desc all api request
 * @date 2017/11/11
 * @email lei.lu@e-at.com
 */
public interface ApiStores {
    //登录
    @FormUrlEncoded
    @POST(Api.login)
    Observable<LoginEntity> login(@FieldMap Map<String, String> params);

    //获取转介绍活动信息
    @FormUrlEncoded
    @POST(Api.getShareLogInfo)
    Observable<PromotionEntity> getShareLogInfo(@FieldMap Map<String, String> params);

    //获取验证码
    @FormUrlEncoded
    @POST(Api.getSMSCode)
    Observable<BaseEntity> getSMSCode(@FieldMap Map<String, String> params);

    //获取语音验证码
    @FormUrlEncoded
    @POST(Api.getVoiceCode)
    Observable<BaseEntity> getVoiceCode(@FieldMap Map<String, String> params);

    //获取闪屏广告
    @POST(Api.getAdList)
    Observable<AdListEntity> getAds();
}
