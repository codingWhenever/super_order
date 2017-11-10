package com.e_at.super_order.mvp.ui.activity;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.e_at.eatlibrary.injection.From;
import com.e_at.super_order.R;
import com.e_at.super_order.application.OrderApplication;
import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.presenter.impl.LoginPresenterImpl;
import com.e_at.super_order.mvp.view.LoginView;
import com.e_at.super_order.observer.OperationObserver;
import com.e_at.super_order.utils.Constants;
import com.e_at.super_order.utils.DeviceUtil;
import com.e_at.super_order.utils.NetworkUtil;
import com.e_at.super_order.utils.RegexUtil;
import com.e_at.super_order.utils.SpConfigUtil;
import com.e_at.super_order.utils.ToastUtil;

import org.jetbrains.annotations.Nullable;

/**
 * LoginActivity
 *
 * @author leo
 * @desc 登录页面
 * @date 2017/11/4
 * @email lei.lu@e-at.com
 */
public class LoginActivity extends BaseActivity implements LoginView {
    @From(R.id.et_name)
    private EditText etPhone;
    @From(R.id.et_code)
    private EditText etCode;

    @From(R.id.btn_voice_code)
    private TextView btnVoiceCode;
    @From(R.id.btn_x)
    private ImageButton btnClean;
    @From(R.id.btn_login)
    private Button btnLogin;

    private static final int CODE_LIMIT_COUNT = 4;
    private LoginPresenterImpl mPresenter;
    private int needLoginType;//需要登录权限

    @Override
    public void initView(Bundle savedInstanceState) {
        setTitle("登录");
        btnVoiceCode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        btnVoiceCode.setAutoLinkMask(Linkify.ALL);

        etPhone.addTextChangedListener(mPhoneTextWatcher);
        etCode.addTextChangedListener(mCodeTextWatcher);
        mPresenter = new LoginPresenterImpl(this);

    }

    @Override
    protected void onResume() {
        isNeedShowDefault = false;
        super.onResume();

        //修改手机号码之后默认填写新号码
        if (getIntent().hasExtra("newPhone")) {
            etPhone.setText(getIntent().getStringExtra("newPhone"));
        }
        if (getIntent().hasExtra(Constants.STR_NEED_LOGIN_TYPE)) {
            needLoginType = getIntent().getIntExtra(Constants.STR_NEED_LOGIN_TYPE, 0);
        }

    }

    TextWatcher mPhoneTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            btnClean.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            String phone = etPhone.getText().toString().trim();
            String code = etCode.getText().toString().trim();

            if (RegexUtil.isMobileSimple(phone) && !TextUtils.isEmpty(code) && code.length() == CODE_LIMIT_COUNT) {
                enableLogin();
            } else {
                disabledLogin();
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher mCodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String phone = etPhone.getText().toString().trim();
            if (s.length() == CODE_LIMIT_COUNT && RegexUtil.isMobileSimple(phone)) {
                enableLogin();
            } else {
                disabledLogin();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_simple;
    }


    public void login(View view) {
        if (!NetworkUtil.checkNetwork(mContext)) {
            return;
        }
        String phone = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();

        if (!RegexUtil.isMobileSimple(phone)) {
            ToastUtil.showToast(mContext, "请输入正确手机号");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.showToast(mContext, "请输入验证码");
            return;
        }
        mPresenter.login(phone, code);

    }

    @Override
    public void loginSuccess(LoginEntity loginEntity) {

        showToast("登录成功");
        saveUserInfo(loginEntity);
        //todo
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        if (needLoginType > 0) {
            intent.putExtra(Constants.STR_NEED_LOGIN_TYPE, needLoginType);
            startActivity(intent);
        } else {
            if (getIntent().hasExtra("extInfo")) {
                setResult(RESULT_OK, new Intent().putExtra("extInfo", getIntent().getStringExtra("extInfo")));
            } else {
                setResult(RESULT_OK);
            }
        }

        OrderApplication.getInstance().getOperationObserver().setOperationType(OperationObserver.STATUS_TYPE_LOGIN);
        LoginActivity.this.finish();
        registerJPush(loginEntity.getResponse().getPhone());



    }


    private void registerJPush(String phone) {
//        JPushInterface.setAliasAndTags(getApplicationContext(),
//                phoneNum,
//                null,
//                new TagAliasCallback() {
//                    @Override
//                    public void gotResult(int i, String s, Set<String> set) {
//
//                    }
//                });
    }

    private void saveUserInfo(LoginEntity loginEntity) {
        SpConfigUtil.setDeviceId(LoginActivity.this, DeviceUtil.getMACAddress(LoginActivity.this.getApplicationContext()));
        SpConfigUtil.setToken(LoginActivity.this, TextUtils.isEmpty(loginEntity.getResponse().getToken()) ? "" : loginEntity.getResponse().getToken());
        SpConfigUtil.setNickname(LoginActivity.this, TextUtils.isEmpty(loginEntity.getResponse().getNickname()) ? "" : loginEntity.getResponse().getNickname());
        SpConfigUtil.setPhone(LoginActivity.this, TextUtils.isEmpty(loginEntity.getResponse().getPhone()) ? "" : loginEntity.getResponse().getPhone());
        SpConfigUtil.setPlatformMemberId(LoginActivity.this, loginEntity.getResponse().getPlatformMemberId());
    }

    @Override
    public void loginFailed(String message) {

        showToast(message);
    }

    @Override
    public void enableLogin() {
        btnLogin.setClickable(true);
        btnLogin.setAlpha(1.0f);
    }

    @Override
    public void disabledLogin() {
        btnLogin.setClickable(false);
        btnLogin.setAlpha(0.5f);
    }

    @Override
    public void showToast(String message) {
        ToastUtil.showToast(mContext, message);
    }

    @Override
    public void showDefaultPage(int type) {

    }


    @Override
    public void retry() {

    }

    @Override
    public void showLoading(@Nullable String loadingMessage) {
        showLoadingDialog("登录中...");
    }


}
