package com.ytlz.baseproject.presenter.Splash;

import com.ytlz.baseproject.moudle.resopnse.SplashResponse;

/**
 * Created by wyb on 2019-09-23.
 */

public interface SplashView {

    void verificationToken();

    void loadSplashImgs(SplashResponse splashResponse);

    void loadSplashImgsError(String mes);
}
