package com.e_at.super_order.mvp.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.e_at.eatlibrary.injection.From;
import com.e_at.super_order.R;
import com.e_at.eatlibrary.utils.ImageLoader;

import org.jetbrains.annotations.Nullable;

/**
 * ADActivity
 *
 * @author leo
 * @desc 广告页面
 * @date 2017/11/13
 * @email lei.lu@e-at.com
 */
public class ADActivity extends BaseActivity {
    private static int AUTO_DELAY_MILLIS = 3000;
    private static String AD_IMG;
    @From(R.id.btnJumpOver)
    private TextView btnJumpOver;

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showDefaultPage(int type) {

    }

    @Override
    public void retry() {

    }

    @Override
    public void showLoading(@Nullable String loadingMessage) {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public boolean setImmersive() {
        return true;
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(mRunnable, 1000);
            if (AUTO_DELAY_MILLIS > 0) {
                btnJumpOver.setText("跳过 " + AUTO_DELAY_MILLIS-- + "s");
            } else {
                jumpOverAuto(0);
            }
        }
    };

    private void jumpOverAuto(long delayMillis) {
        final Intent intent = new Intent();
        intent.setClass(this, HomeActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);

            }
        }, delayMillis);
        ADActivity.this.finish();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("imgPath")) {
                AD_IMG = intent.getStringExtra("imgPath");
            }
            if (intent.hasExtra("duration")) {
                AUTO_DELAY_MILLIS = intent.getIntExtra("duration", 3000);
            }
        }
        btnJumpOver.setText("跳过 " + AUTO_DELAY_MILLIS + "s");

        if (!TextUtils.isEmpty(AD_IMG)) {
            ImageLoader.loadWithGlide(mContext, AD_IMG, (ImageView) findViewById(R.id.iv_ad));
        }
        mHandler.postDelayed(mRunnable, 0);
    }


    public void jumpOverByPress(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ad;
    }
}
