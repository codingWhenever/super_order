package com.e_at.super_order.observer;

import java.util.Observable;

/**
 * OperationObserver
 *
 * @author leo
 * @desc
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public class OperationObserver extends Observable {
    public static final int STATUS_TYPE_LOGIN = 1;//登入
    public static final int STATUS_TYPE_LOGOUT = 2;//登出
    public static final int STATUS_TYPE_UPDATE_CALL_NO = 3;//刷新订单信息
    public static final int STATUS_REFRESH_HOME_PAGE = 4;//下单成功后刷新首页
    private int type;

    public void setOperationType(int type) {
        this.type = type;
        setChanged();
        notifyObservers();
    }

    public int getOperationType() {
        return this.type;
    }
}
