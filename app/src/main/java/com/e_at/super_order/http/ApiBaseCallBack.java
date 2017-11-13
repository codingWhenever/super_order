package com.e_at.super_order.http;


public interface ApiBaseCallBack<T> {
    void onSuccess(T data);

    void onFailure(String message);

    void onComplete();

    void onAppUpdate(T data);

    void onBadToken();
}
