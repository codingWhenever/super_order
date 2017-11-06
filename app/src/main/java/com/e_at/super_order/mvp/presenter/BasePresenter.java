package com.e_at.super_order.mvp.presenter;


/**
 * BasePresenter
 *
 * @author leo
 * @desc presenter基类
 * @date 2017/11/4
 * @email lei.lu@e-at.com
 */
public interface BasePresenter<T> {

    void attachView(T view);

    void detachView();
}
