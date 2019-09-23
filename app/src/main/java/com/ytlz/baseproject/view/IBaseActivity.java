package com.ytlz.baseproject.view;

import android.view.View;

/**
 * Created by wyb on 2019-09-18.
 */

public interface IBaseActivity {

    /**
     * @param regristedBus 是否注册BusUtils
     * @param busTag 添加的粘性事件的tag
     */
    public void setRegristedBus(Boolean regristedBus, String... busTag);

    /**
     * @param color 设置导航栏颜色
     */
    public void setNavBarColor(int color);

    /**
     * @param isVisible 设置导航栏是否可见
     */
    public void setNavBarVisibility(boolean isVisible);

    /**
     * 设置全屏（状态栏取消显示，导航栏不变）
     */
    public void setFullScreen();

    /**
     * 设置非全屏（状态栏取消显示，导航栏不变）
     */
    public void setNonFullScreen();

    /**
     * 设置是否显示下拉状态栏
     *
     * @param isVisible true,显示下拉的通知栏
     *                  默认为不显示
     */
    public void setNotificationBarVisibility(boolean isVisible);

    /**
     * 设置非全屏（状态栏取消显示，导航栏不变）
     */
    public void steepStatusBar();

    /**
     * @param color      状态栏的颜色
     * @param parentView 布局文件的最外层布局
     */
    public void setStatusBarColor(int color, View parentView);

    /**
     * @param isVisible 是否显示
     */
    public void setStatusVisibily(boolean isVisible);

    /**
     * 设置状态栏的风格
     *
     * @param isLightMode
     */
    public void setStatusBarMode(boolean isLightMode);

    /**
     * 设置状态栏是否可见
     *
     * @param isVisible true 可见，false 不可见
     *                  默认可见
     */
    public void setStatusBarVisibility(boolean isVisible);


    /**
     * 直接跳转到主界面
     */
    public void startHomeActivity();


}
