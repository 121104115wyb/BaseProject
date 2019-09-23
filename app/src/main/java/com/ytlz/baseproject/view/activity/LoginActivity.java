package com.ytlz.baseproject.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.ytlz.baseproject.R;
import com.ytlz.baseproject.common.AppUserConstants;
import com.ytlz.baseproject.moudle.request.LoginMoudle;
import com.ytlz.baseproject.moudle.resopnse.UserMoudle;
import com.ytlz.baseproject.presenter.login.LoginPresenter;
import com.ytlz.baseproject.presenter.login.LoginView;
import com.ytlz.baseproject.view.BaseActivity;

import butterknife.BindView;

import static com.ytlz.baseproject.common.AppUserConstants.PassWordCanche;
import static com.ytlz.baseproject.common.AppUserConstants.UserNameCanche;
import static com.ytlz.baseproject.common.BusConstants.USER_LOGIN;

/**
 * Created by wyb on 2019-09-20.
 */

public class LoginActivity extends BaseActivity implements LoginView {


    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    LoginPresenter loginPresenter;

    @BusUtils.Bus(tag = USER_LOGIN)
    public void setLoginData() {
        username.setText(SPUtils.getInstance().getString(UserNameCanche, ""));
        password.setText(SPUtils.getInstance().getString(PassWordCanche, ""));
    }


    @Override
    public void initParms(Bundle parms) {
        hideActionBar();
        setFullScreen();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        loginPresenter = LoginPresenter.getInstance();
        loginPresenter.setLoginView(this);
        setRegristedBus(true, USER_LOGIN);
    }

    @Override
    public void setListener() {
        //防止重复点击，1.5s内多次点击无效
        ClickUtils.applySingleDebouncing(loginBtn, 1500, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void backPressed() {
        //需要记录下状态
        finish();
    }

    @SuppressLint("MissingPermission")
    public void loginClicked() {
        if (TextUtils.isEmpty(username.getText().toString())) {
            showShortToast(R.string.toast_username_empty);
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            showShortToast(R.string.toast_password_empty);
            return;
        }
        //请求登录操作
        LoginMoudle loginMoudle = LoginMoudle.getInstance();
        loginMoudle.setMac(PhoneUtils.getDeviceId());
        String md5Str = EncryptUtils.encryptMD5ToString(password.getText().toString());
        loginMoudle.setPassWord(md5Str);
        loginMoudle.setUserName(username.getText().toString());
        loginPresenter.login(loginMoudle);
    }

    @Override
    public void loginSuccess(UserMoudle userMoudle) {
        //登录成功后，需要把token，refresh_token缓存起来
        showShortToast(R.string.toast_login_success);
        SPUtils.getInstance().put(AppUserConstants.AppToken, userMoudle.getToken());
        SPUtils.getInstance().put(AppUserConstants.ExitApp, AppUserConstants.HangTag);
        SPUtils.getInstance().put(AppUserConstants.AppRefreshToken, userMoudle.getResfresh_token());
        SPUtils.getInstance().put(UserNameCanche, username.getText().toString());
        SPUtils.getInstance().put(PassWordCanche, password.getText().toString());
        ActivityUtils.startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String mes) {
        showShortToast(mes);
    }
}
