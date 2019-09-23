package com.ytlz.baseproject;

import android.app.Application;
import android.content.pm.ApplicationInfo;

/**
 * Created by wyb on 2019-09-20.
 */

public class BaseApplication extends Application {

    static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        init();
    }


    public BaseApplication getInstance() {
        return baseApplication;
    }

    //do your bussion
    void init() {




    }


}
