package com.e_at.super_order.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * SharedPreferencesUtil
 *
 * @author leo
 * @desc
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public class SharedPreferencesUtil {

    private static final String FILE_NAME = "dykj_data";

    public static void setParam(Context context, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        }

//        editor.apply();
        editor.commit();
    }

    public static Object getParam(Context context, String key, Object defaultObj) {
        String type = defaultObj.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);

        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObj);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObj);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        }

        return null;
    }

    public static void remove(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS).edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);

    }

    public static void clear(Context context, String key) {
        SharedPreferences.Editor editor = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS).edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);

    }

    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        return sp.contains(key);
    }


    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_MULTI_PROCESS);
        return sp.getAll();
    }

    private static class SharedPreferencesCompat {

        private static final Method sApplyMethod = findApplyMethod();

        private static Method findApplyMethod() {
            Class clz = SharedPreferences.Editor.class;

            try {
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static void apply(SharedPreferences.Editor editor) {
            if (sApplyMethod != null) {
                try {
                    sApplyMethod.invoke(editor);
                    return;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
            editor.commit();
        }


    }
}
