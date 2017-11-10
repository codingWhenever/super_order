package com.e_at.super_order.http;


import android.util.Base64;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParamsUtil {

    /**
     * 签名参数
     * @paramters 
     * @return 
     */
    public static Map<String, String> packageParams(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                jsonObject.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                Logger.e(e.getMessage());
            }
        }

        Map<String, String> paramsData = new HashMap<>();
        Logger.json(jsonObject.toString());
        paramsData.put("jsonParams", encodeBase64(jsonObject.toString()));
        long timestamp = System.currentTimeMillis();
        paramsData.put("timestamp", String.valueOf(timestamp));
        return paramsData;
    }

    /**
     * Base64编码.
     */
    private static String encodeBase64(String input) {
        try {
            return Base64.encodeToString(input.getBytes("utf-8"), Base64.DEFAULT);
        } catch (Exception e) {
            return "";
        }
    }
}
