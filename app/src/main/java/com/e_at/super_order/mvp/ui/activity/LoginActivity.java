package com.e_at.super_order.mvp.ui.activity;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
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
import com.e_at.super_order.mvp.entity.BaseEntity;
import com.e_at.super_order.mvp.entity.LoginEntity;
import com.e_at.super_order.mvp.entity.PromotionEntity;
import com.e_at.super_order.mvp.presenter.impl.LoginPresenterImpl;
import com.e_at.super_order.mvp.view.LoginView;
import com.e_at.super_order.observer.OperationObserver;
import com.e_at.super_order.utils.Constants;
import com.e_at.eatlibrary.utils.DeviceUtil;
import com.e_at.eatlibrary.utils.NetworkUtil;
import com.e_at.eatlibrary.utils.RegexUtil;
import com.e_at.super_order.utils.SpConfigUtil;
import com.e_at.eatlibrary.utils.ToastUtil;

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
    private String phone;
    private int count = 0;

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

    /**
     * 获取验证码
     *
     * @return
     * @paramters
     */
    public void getVerCode(View view) {
        if (!NetworkUtil.checkNetwork(mContext)) {
            return;
        }
        phone = etPhone.getText().toString().trim();
        if (!RegexUtil.isMobileSimple(phone)) {
            ToastUtil.showToast(mContext, "请输入正确手机号");
            return;
        }
        if (count > 0) {
            return;
        }

        mPresenter.getSMSCode(phone);

    }

    /**
     * 登录
     *
     * @return
     * @paramters
     */
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

    /**
     * 清空手机号码
     *
     * @return
     * @paramters
     */
    public void cleanPhone(View view) {
        phone = etPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone)) {
            etPhone.setText("");
        }
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


        mPresenter.getShareLogInfo(loginEntity.getResponse().getPhone(),
                String.valueOf(loginEntity.getResponse().getPlatformMemberId()));
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

    /**
     * 本地保存用户信息
     *
     * @return
     * @paramters
     */
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
    public void getShareInfoSuccess(PromotionEntity data) {
        if (data.status == 1) {
            SpConfigUtil.setHasPromition(mContext, true);
            SpConfigUtil.setShareURLPrefix(mContext, data.getResponse().getShareUrl());
            SpConfigUtil.setPromotionId(mContext, data.getResponse().getPromotionId());
            SpConfigUtil.setMemberId(mContext, data.getResponse().getMemberId());
        } else if (data.status == 2) {
            //没有活动保存默认分享地址及其标题和描述
            SpConfigUtil.setHasPromition(mContext, false);
        }
        if (data.status != -1) {
            SpConfigUtil.setDefaultShareURL(mContext, data.getResponse().getDefaultUrl());
            SpConfigUtil.setDefaultShareTitle(mContext, data.getResponse().getDefaultTitle());
            SpConfigUtil.setDefaultShareDesc(mContext, data.getResponse().getDefaultDesc());
        }
    }

    @Override
    public void getInfoFailed(String message) {

    }

    @Override
    public void getSMSCodeSuccess(BaseEntity baseEntity) {
        count = 60;
        codeCounter(findViewById(R.id.tv_code));
        showToast(baseEntity.info);
        jumpToCodeText();

    }

    @Override
    public void getSMSCodeFailed(String message) {
        showToast(message);
    }

    private void jumpToCodeText() {
        phone = etPhone.getText().toString().trim();
        if (RegexUtil.isMobileSimple(phone)) {
            etCode.requestFocus();
            CharSequence code = etCode.getText();
            if (code instanceof Spannable) {
                Selection.setSelection((Spannable) code, code.length());
            }
        }
    }

    @Override
    public void codeCounter(final View view) {
        if (count > 0) {
            findViewById(R.id.ll_text).setVisibility(View.VISIBLE);
        }
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                count--;
                if (count > 0) {
                    ((TextView) view).setText("重新获取(" + count + "s)");
                    codeCounter(view);
                } else {
                    ((TextView) view).setText("获取验证码");
                }
            }
        }, 1000);
    }

    @Override
    public void getVoiceCodeSuccess(BaseEntity data) {
        showToast("您将收到来自02566040868的语音电话，请注意接听。");
    }

    @Override
    public void getVoiceCodeFailed(String message) {

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
