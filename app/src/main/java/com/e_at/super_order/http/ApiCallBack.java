package com.e_at.super_order.http;

/**
 * ApiCallBack
 *
 * @author leo
 * @desc
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public interface ApiCallBack<T> {
    void onSuccess(T data);

    void onFailure(String message);

    void onComplete();
}
