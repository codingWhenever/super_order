package com.e_at.super_order.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.e_at.super_order.R;
import com.e_at.super_order.mvp.entity.AdListEntity;
import com.e_at.super_order.mvp.presenter.SplashPresenter;
import com.e_at.super_order.mvp.presenter.impl.SplashPresenterImpl;
import com.e_at.super_order.mvp.view.SplashView;
import com.e_at.eatlibrary.utils.NetworkUtil;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

/**
 * SplashActivity
 *
 * @author leo
 * @desc 闪屏页
 * @date 2017/11/4
 * @email lei.lu@e-at.com
 */
public class SplashActivity extends BaseActivity implements SplashView {
    private SplashPresenter mSplashPresenter;
    private boolean hasAd = false;
    private static int WAITING_TIME = 1500;
    private AdListEntity.AdEntity mAdEntity;

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
        mSplashPresenter = new SplashPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        isNeedShowDefault = false;
        super.onResume();
        if (NetworkUtil.isNetworkAvailable(mContext) && NetworkUtil.isNiceNetwork(mContext)) {
            mSplashPresenter.getAds();
        } else {
            jumpOverAuto(WAITING_TIME);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void jumpToAdPage(AdListEntity data) {
        try {
            List<AdListEntity.AdEntity> list = data.getResponse();
            if (list != null && list.size() > 0) {
                hasAd = true;
                Random random = new Random();
                int index = 0;
                if (list.size() > 1) {
                    index = random.nextInt(list.size() - 1);
                }
                mAdEntity = list.get(index);
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }

        jumpOverAuto(WAITING_TIME);
    }

    private Intent mIntent;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIntent != null) {
                startActivity(mIntent);
                SplashActivity.this.finish();
                if (mHandler != null) {
                    mHandler.removeCallbacks(mRunnable);
                }

            }
        }
    };

    @Override
    public void jumpOverAuto(int delay) {
        mIntent = new Intent();
        if (hasAd && mAdEntity != null) {
            mIntent.setClass(mContext, ADActivity.class);
            mIntent.putExtra("imgPath", mAdEntity.adImgPath);
            mIntent.putExtra("duration", mAdEntity.adDuration);
        } else {
            mIntent.setClass(mContext, MainActivity.class);
        }
        mHandler.postDelayed(mRunnable, delay);
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
        super.onDestroy();
    }
}
