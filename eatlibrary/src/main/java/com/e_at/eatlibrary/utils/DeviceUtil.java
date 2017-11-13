package com.e_at.eatlibrary.utils;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.logging.Logger;

public class DeviceUtil {
    /**
     * 获取手机mac地址
     *
     * @param context
     * @return
     */
    public static String getMACAddress(Context context) {
        String macAddress = "000000000000";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();

        if (info != null) {
            if (!TextUtils.isEmpty(info.getMacAddress())) {
                macAddress = info.getMacAddress().replace(":", "");
            }
        }
        return macAddress;
    }

    /**
     * 获取设备id
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {

        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        deviceId.append("dykj");
        try {

            //wifi mac地址
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!TextUtils.isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);
                return deviceId.toString();
            }

            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!TextUtils.isEmpty(imei)) {
                deviceId.append("imei");
                deviceId.append(imei);
                return deviceId.toString();
            }

            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!TextUtils.isEmpty(sn)) {
                deviceId.append("sn");
                deviceId.append(sn);
                return deviceId.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        com.orhanobut.logger.Logger.d("getDeviceId : " + deviceId.toString());
        return deviceId.toString();
    }
}
