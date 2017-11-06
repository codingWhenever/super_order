package com.e_at.super_order.mvp.view;


/**
 * BaseView
 * @desc view基类
 * @author leo
 * @date 2017/11/4
 * @email lei.lu@e-at.com
 */
public interface BaseView {

    void showToast(String message);

    void showDefaultPage(int type);

    void retry();

    void setTitle(String title);
}
