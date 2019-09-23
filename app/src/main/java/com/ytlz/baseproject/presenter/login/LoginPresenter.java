package com.ytlz.baseproject.presenter.login;

import com.ytlz.baseproject.moudle.request.LoginMoudle;
import com.ytlz.baseproject.moudle.resopnse.UserMoudle;

/**
 * Created by wyb on 2019-09-20.
 */

public class LoginPresenter {

    LoginView loginView;

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public static LoginPresenter getInstance() {
        return new LoginPresenter();
    }

    /**
     * 登录操作
     *
     * 如果没有注册功能，第一次登录成功后，返回 服务器生成的token，refresh_token,head（默认的头像图片）
     * @param loginMoudle
     */
    public void login(LoginMoudle loginMoudle) {
        if (loginMoudle == null) {
            loginView.loginFailed("loginMoudle is null");
        }else
            loginView.loginSuccess(new UserMoudle());
    }


}
