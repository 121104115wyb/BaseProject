package com.ytlz.baseproject.view;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ArrayUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.BusUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ytlz.baseproject.R;
import com.ytlz.baseproject.common.AppUserConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ytlz.baseproject.view.BaseActivity.PermissionCallBack.REQUEST_DRAWOVERLAYS;


/**
 * Created by wyb on 2019-09-19.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {


    @Override
    public void setRegristedBus(Boolean regristedBus, String... busTag) {
        this.regristedBus = regristedBus;
        if (regristedBus) BusUtils.register(this);
        setBusTags(busTag);

    }

    @Override
    public void setNavBarColor(int color) {
        BarUtils.setNavBarColor(this, color);
    }

    @Override
    public void setNavBarVisibility(boolean isVisible) {
        BarUtils.setNavBarVisibility(this, isVisible);

    }

    @Override
    public void setFullScreen() {
        if (!ScreenUtils.isFullScreen(this)) {
            ScreenUtils.setFullScreen(this);
        }
    }

    @Override
    public void setNonFullScreen() {
        if (ScreenUtils.isFullScreen(this)) {
            ScreenUtils.setNonFullScreen(this);
        }
    }

    @Override
    public void setNotificationBarVisibility(boolean isVisible) {
        BarUtils.setNotificationBarVisibility(isVisible);
    }

    //沉浸式界面，如果activity现实默认标题栏，需要把标题栏隐藏掉
    @Override
    public void steepStatusBar() {
        setStatusBarVisibility(false);
        setNavBarVisibility(false);
        hideActionBar();
    }

    @Override
    public void setStatusBarColor(int color, View parentView) {
        BarUtils.setStatusBarColor(this, color);
        BarUtils.addMarginTopEqualStatusBarHeight(parentView);
    }

    @Override
    public void setStatusVisibily(boolean isVisible) {
        BarUtils.setStatusBarVisibility(this, isVisible);
    }

    @Override
    public void setStatusBarMode(boolean isLightMode) {
        BarUtils.setStatusBarLightMode(this, isLightMode);
    }

    @Override
    public void setStatusBarVisibility(boolean isVisible) {
        BarUtils.setStatusBarVisibility(this, isVisible);
    }

    @Override
    public void startHomeActivity() {
        ActivityUtils.startHomeActivity();
    }

//    /**
//     * 是否沉浸状态栏
//     **/
//    private boolean isSetStatusBar = false;
//    /**
//     * 是否允许全屏
//     **/
//    private boolean mAllowFullScreen = false;
//    /**
//     * 是否禁止旋转屏幕
//     **/
//    private boolean isAllowScreenRoate = true;
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 存储上次按下返回键的时间
     */
    private long lastTimeMills = 0l;
    /**
     * 间隔时间
     */
    private long intervalTimeMills = 2000l;
    private Unbinder unbinder = null;
    private List<String> BusTags = new ArrayList<>();
    private boolean regristedBus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
        View mView = bindView();
        if (null == mView) {
            mContextView = LayoutInflater.from(this)
                    .inflate(bindLayout(), null);
        } else {
            mContextView = mView;
        }
        setContentView(mContextView);
        unbinder = ButterKnife.bind(this);
        initView(mContextView);
        setListener();
        doBusiness(this);
    }

    public void hideActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }


    /**
     * 请求悬浮窗权限
     */
    public void requestDrawOverly() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            PermissionUtils.requestDrawOverlays(new PermissionCallBack(REQUEST_DRAWOVERLAYS));
        }
    }

    /**
     * 申请允许系统设置权限
     */
    public void requestWriteSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionUtils.requestWriteSettings(new PermissionCallBack(PermissionCallBack.REQUEST_WRITE_SETTING));
        }
    }

    public class PermissionCallBack implements PermissionUtils.SimpleCallback {

        public final static String REQUEST_WRITE_SETTING = "request_write_setting";
        public final static String REQUEST_DRAWOVERLAYS = "request_drawoverlays";
        private String requestTag;

        public PermissionCallBack(String requestTag) {
            this.requestTag = requestTag;
        }

        @Override
        public void onGranted() {
            rPCallBack(requestTag, true);
        }

        @Override
        public void onDenied() {
            rPCallBack(requestTag, false);
        }
    }


    /**
     * 申请权限
     *
     * @param permissions
     */
    public void requestPermission(@PermissionConstants.Permission String... permissions) {
        PermissionUtils.permission(permissions).callback(new PermissionUtils.FullCallback() {
            @Override
            public void onGranted(List<String> permissionsGranted) {
                LogUtils.dTag(TAG, "申请权限");

            }

            @Override
            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                LogUtils.dTag(TAG, "申请权限失败" + permissionsDenied);
            }
        }).request();
    }

    /**
     * 悬浮窗和修改系统权限
     *
     * @param tag        权限名称
     * @param permission 是否同意
     */
    public void rPCallBack(String tag, boolean permission) {

    }


    /**
     * [初始化参数]
     * 只能接收数据，不能直接装载数据，因为此方法在initView之前
     *
     * @param parms
     */
    public abstract void initParms(Bundle parms);

    /**
     * [绑定布局] or [绑定视图]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     *
     * @param view
     */
    public abstract void initView(final View view);

    /**
     * [设置监听]
     */
    public abstract void setListener();

    /**
     * [业务操作]
     *
     * @param mContext
     */
    public abstract void doBusiness(Context mContext);

    /**
     * [绑定视图] or [绑定布局]
     *
     * @return
     */
    public abstract View bindView();

    /**
     * 返回键
     */
    public abstract void backPressed();

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showShortToast(String msg) {
        ToastUtils.showShort(msg);
    }

    protected void showLongToast(String msg) {
        ToastUtils.showLong(msg);
    }

    protected void showShortToast(int resId) {
        ToastUtils.showShort(resId);
    }

    protected void showLongToast(int resId) {
        ToastUtils.showLong(resId);
    }

    private void setBusTags(String... busTag) {
        if (BusTags == null) BusTags = new ArrayList<>();
        else
            BusTags.addAll(ArrayUtils.asArrayList(busTag));
    }

    //点击两次退出app
    protected void doubleClickExitApp() {
        long currentTimeMills = System.currentTimeMillis();
        if (currentTimeMills - lastTimeMills > intervalTimeMills) {
            showShortToast(R.string.toast_double_click_exitApp);
            lastTimeMills = currentTimeMills;
        } else {
            SPUtils.getInstance().put(AppUserConstants.ExitApp,AppUserConstants.ExitTag);
            AppUtils.exitApp();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        backPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycle();
    }

    private void recycle() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (regristedBus) {
            for (String tag : BusTags) {
                BusUtils.removeSticky(tag);
            }
            BusUtils.unregister(this);
        }
    }


}
