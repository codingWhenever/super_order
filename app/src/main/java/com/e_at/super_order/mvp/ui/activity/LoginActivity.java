package com.e_at.super_order.mvp.ui.activity;


import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.e_at.eatlib.injection.From;
import com.e_at.super_order.R;
import com.e_at.super_order.mvp.presenter.LoginPresenterImpl;
import com.e_at.super_order.mvp.view.LoginView;

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

    private LoginPresenterImpl mPresenter;
    @Override
    public void initView(Bundle savedInstanceState) {
        setTitle("登录");
        btnVoiceCode.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        btnVoiceCode.setAutoLinkMask(Linkify.ALL);

        etPhone.addTextChangedListener(mTextWatcher);
        etCode.addTextChangedListener(mTextWatcher);
        mPresenter = new LoginPresenterImpl();


    }

    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_simple;
    }


    @Override
    public void loginSuccess(String message) {

    }

    @Override
    public void loginFailed(String message) {

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
}
