package com.ytlz.baseproject.presenter.login;

import com.ytlz.baseproject.moudle.resopnse.UserMoudle;

/**
 * Created by wyb on 2019-09-20.
 */

public interface LoginView {


    void loginSuccess(UserMoudle userMoudle);

    void loginFailed(String mes);

}
