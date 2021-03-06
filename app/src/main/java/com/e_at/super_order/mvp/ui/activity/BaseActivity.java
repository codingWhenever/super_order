package com.e_at.super_order.mvp.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.e_at.eatlibrary.immersionbar.ImmersionBar;
import com.e_at.eatlibrary.injection.Ioc;
import com.e_at.eatlibrary.utils.NetworkUtil;
import com.e_at.super_order.R;
import com.e_at.super_order.application.OrderApplication;
import com.e_at.super_order.mvp.presenter.BasePresenter;
import com.e_at.super_order.mvp.view.BaseView;
import com.e_at.super_order.utils.Constants;

import java.util.LinkedList;
import java.util.List;

import static com.e_at.super_order.utils.Constants.TYPE_DEFAULT_PAGE_NET_ERROR;

/**
 * BaseActivity
 *
 * @author leo
 * @desc activity基类
 * @date 2017/11/6
 * @email lei.lu@e-at.com
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected Context mContext;
    protected View defaultPage, mainPage;
    public int defaultPageType = TYPE_DEFAULT_PAGE_NET_ERROR;
    public boolean isNeedShowDefault = true;//是否需要显示无网或者加载失败的占位图

    private static List<BaseActivity> sActivityList = new LinkedList<>();


    public BasePresenter<BaseView> mBasePresenter;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Ioc.initInjectedView(this, getWindow().getDecorView());
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        Ioc.initInjectedView(this, getWindow().getDecorView());
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        Ioc.initInjectedView(this, getWindow().getDecorView());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init(savedInstanceState);

    }

    public abstract void initView(Bundle savedInstanceState);

    private void init(Bundle savedInstanceState) {
        setContentView(getLayoutId());
        this.mContext = this;
        initView(savedInstanceState);

        boolean isImmersive = setImmersive();
        if (isImmersive) {
            setFitSystemWindows();
            initDefaultImmersionBar();
        } else {
            initImmersionBar();
        }
        if (!sActivityList.contains(this)) {
            sActivityList.add(this);
        }
    }

    private void setFitSystemWindows() {
        ViewGroup mContentView = (ViewGroup) getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            mChildView.setFitsSystemWindows(true);
        }
    }

    /**
     * 设置默认样式
     *
     * @return
     * @paramters
     */
    private void initDefaultImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.color_white)
                .statusBarDarkFont(true, 0.2f)
                .navigationBarColor(R.color.color_black)
                .init();
    }

    /**
     * 根据各页面实际情况自定义
     *
     * @return
     * @paramters
     */
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    public abstract boolean setImmersive();

    public abstract int getLayoutId();

    public void reload(View view) {
    }

    @Override
    protected void onResume() {
        super.onResume();

        OrderApplication.getInstance().curActivity = this;

        displayDefaultPageOrNot(defaultPageType);
    }

    public void findPageById() {
//        mainPage = findViewById(R.id.layout_main);
//        defaultPage = findViewById(R.id.layout_default);
    }

    /**
     * 显示默认占位图
     *
     * @return
     * @paramters
     */
    private void displayDefaultPageOrNot(int defaultPageType) {
        if (!isNeedShowDefault) {
            return;
        }
        if (!NetworkUtil.isNetworkAvailable(this)) {
            if (defaultPage != null) {
                defaultPage.setVisibility(View.VISIBLE);
//                defaultPage.findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        reload(v);
//                    }
//                });
                if (defaultPageType == Constants.TYPE_DEFAULT_PAGE_LOAD_FAILURE) {
//                    ((TextView) defaultPage.findViewById(R.id.tv_message)).setText(getString(R.string.str_load_failure_tip));
                } else {
//                    ((TextView) defaultPage.findViewById(R.id.tv_message)).setText(getString(R.string.str_net_error_tip));
                }
            }
            if (mainPage != null) {
                mainPage.setVisibility(View.GONE);
            }
        } else {
            if (mainPage != null) {
                mainPage.setVisibility(View.VISIBLE);
            }
            if (defaultPage != null) {
                defaultPage.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
    }

    protected void setRightText(String text) {
        TextView rightText = (TextView) findViewById(R.id.tv_right_text);
        if (rightText != null && !TextUtils.isEmpty(text)) {
            rightText.setText(text);
            rightText.setVisibility(View.VISIBLE);
        }
    }

    public void goBack(View view) {
        finish();
    }

    public void closeWebView(View view) {
        this.finish();
    }

    public void showCloseBtn(boolean isShow) {
        findViewById(R.id.ic_finish).setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    public ProgressDialog dialog;

    /**
     * 显示加载对话框
     *
     * @return
     * @paramters
     */
    public void showLoadingDialog(@Nullable String info) {
        dialog = ProgressDialog.show(mContext, null, null, true, true);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_view, null);
        dialog.setContentView(view);
        if (!TextUtils.isEmpty(info)) {
            dialog.setCancelable(false);
            dialog.setMessage(info);
        }
        Point size = new Point();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        WindowManager.LayoutParams lps = dialog.getWindow().getAttributes();
        lps.alpha = 0.8f;
        lps.gravity = Gravity.CENTER;
        lps.width = width / 3;
        lps.height = width / 3;
        lps.dimAmount = 0.4f;
        dialog.getWindow().setAttributes(lps);
    }

    /**
     * 隐藏对话框
     *
     * @return
     * @paramters
     */
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
