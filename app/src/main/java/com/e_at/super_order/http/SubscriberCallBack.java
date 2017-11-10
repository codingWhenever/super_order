package com.e_at.super_order.http;


import com.e_at.super_order.mvp.entity.BaseEntity;
import com.orhanobut.logger.Logger;

import rx.Subscriber;

/**
 * SubscriberCallBack
 *
 * @author leo
 * @desc
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public class SubscriberCallBack<T> extends Subscriber<T> {
    private static final int RESPONSE_CODE_SUCCESS = 1;//成功
    //    private static final int RESPONSE_CODE_INVALID_TOKEN = 2;
    private static final int RESPONSE_CODE_ERROR = -1;//失败
    private static final int RESPONSE_CODE_INVALID_TOKEN = -4;//无效token
    private static final int RESPONSE_CODE_UPDATE_APP_VERSION = -7;//有新版本
    private ApiCallBack<T> mApiCallBack;

    public SubscriberCallBack(ApiCallBack<T> apiCallBack) {
        mApiCallBack = apiCallBack;
    }

    @Override
    public void onCompleted() {
        mApiCallBack.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.getMessage());
        mApiCallBack.onFailure("啊哦，哪里好像出问题了~");
    }

    @Override
    public void onNext(T t) {
        Logger.d(t.toString());
        if (t instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) t;
            if (entity.status == RESPONSE_CODE_SUCCESS) {//成功
                mApiCallBack.onSuccess(t);
            } else if (entity.status == RESPONSE_CODE_INVALID_TOKEN) {//无效token

            } else if (entity.status == RESPONSE_CODE_ERROR) {//失败
                mApiCallBack.onFailure(entity.info);
            } else if (entity.status == RESPONSE_CODE_UPDATE_APP_VERSION) {//检测到新版本

            }
        }
    }
}
