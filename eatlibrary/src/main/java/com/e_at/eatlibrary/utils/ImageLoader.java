package com.e_at.eatlibrary.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.e_at.eatlibrary.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * 图片加载包装类
 * Created by lijie on 2016/5/17.
 */
public class ImageLoader {

    private static final String TAG = "ImageLoader";

    /**
     * 加载图片到对应imageview
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadWithGlide(Context context, String imageUrl, ImageView imageView) {
        Log.d(TAG, imageUrl);

        Glide.with(context).load(imageUrl).into(imageView);

    }

    /**
     * 加载图片到对应imageview
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadWithPicasso(Context context, String imageUrl, ImageView imageView) {
        Log.d(TAG, imageUrl);
        Picasso.with(context).load(imageUrl).into(imageView);
    }

    /**
     * 加载图片到对应的imageview 显示默认图片和加载错误图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param imageHolderId
     * @param imageErrorHolderId
     */
    public static void loadWithPlaceHolder(Context context,
                                           String imageUrl,
                                           ImageView imageView,
                                           int imageHolderId,
                                           int imageErrorHolderId) {
        Picasso.with(context).load(imageUrl).placeholder(imageHolderId).error(imageErrorHolderId).into(imageView);
    }

    /**
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadWithPlaceHolderUseDefaPic(Context context,
                                           String imageUrl,
                                           ImageView imageView) {
        Picasso.with(context).load(imageUrl).placeholder(R.drawable.iv_no_photo).error(R.drawable.iv_no_photo).into(imageView);
    }

    /**
     * 将图片裁剪后加载到对应imageview
     *
     * @param context
     * @param imageUrl
     * @param resizeWidth  裁剪后图片宽
     * @param resizeHeight 裁剪后图片高
     * @param resizeMode   裁剪方式  0 : centerCrop   1 : centerInside
     * @param imageView
     */
    public static void loadResize(Context context,
                                  String imageUrl,
                                  int resizeWidth,
                                  int resizeHeight,
                                  int resizeMode,
                                  ImageView imageView) {
        if (resizeMode == 0) {
            Picasso.with(context).load(imageUrl).centerCrop().resize(resizeWidth, resizeHeight).into(imageView);
        } else {
            Picasso.with(context).load(imageUrl).centerInside().resize(resizeWidth, resizeHeight).into(imageView);
        }

    }


    /**
     * 自定义图片转换方式
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param transform 图片转换接口
     */
    public static void loadWith(Context context,
                                String imageUrl,
                                ImageView imageView,
                                Transform transform) {
//        Picasso.with(context).load(imageUrl).transform(transform).into(imageView);
    }


    /**
     * 图片装换接口
     */
    abstract class Transform implements Transformation {
    }
}
