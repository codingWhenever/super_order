package com.e_at.super_order.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


public class MyToastUtil {

    private static Toast mToast;
    private static int mGravity = Gravity.CENTER_VERTICAL;

    /**
     * 非阻塞式显示Toast,防止出现连续点击Toast时的显示问题
     *
     * @param context
     * @param text     显示文本
     * @param duration 持续时长
     * @param gravity  显示位置
     * @return
     */
    public static void showToast(Context context, CharSequence text, int duration, int gravity) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);

        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT, mGravity);
    }
}
