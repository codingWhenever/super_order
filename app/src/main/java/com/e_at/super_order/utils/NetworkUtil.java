/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.e_at.super_order.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * NetworkUtil
 * @desc
 * @author leo
 * @date 2017/11/6
 * @email lei.lu@e-at.com
 */
public class NetworkUtil {

    private static final String TAG = "NetworkUtil";
    private static final String NETWORK_TYPE_WIFI = "WIFI";
    private static final String NETWORK_TYPE_4G = "4G";
    private static final String NETWORK_TYPE_3G = "3G";
    private static final String NETWORK_TYPE_2G = "2G";

    /**
     * 是否有网络链接
     *
     * @param context
     * @return
     */
    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 检查网络连接状态
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
        if (infos != null) {
            for (NetworkInfo info : infos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 检查网络
     *
     * @param context
     * @return
     */
    public static boolean checkNetwork(Context context) {
        boolean result = false;
        result = isNetworkAvailable(context) && isNetworkConnected(context);
        if (!result) {
            MyToastUtil.showToast(context, "网络异常,请稍后尝试");
        }

        return result;

    }


    public static String GetNetworkType(Context context) {

        String strNetworkType = "";

        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                //wifi
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                Log.d(TAG, "Network getSubtypeName : " + _strSubTypeName);

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }

                Log.d(TAG, "Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        }

        Log.d(TAG, "Network Type : " + strNetworkType);

        return strNetworkType;
    }

    /**
     * wifi或4g或3g视为网络状况良好
     * @param context
     * @return
     */
    public static boolean isNiceNetwork(Context context) {
        String currentNetworkType = GetNetworkType(context);
        return currentNetworkType.endsWith(NETWORK_TYPE_WIFI) || currentNetworkType.endsWith(NETWORK_TYPE_4G)
                || currentNetworkType.endsWith(NETWORK_TYPE_3G);
    }
}
