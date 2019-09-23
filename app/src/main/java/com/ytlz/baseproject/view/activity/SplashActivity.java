package com.ytlz.baseproject.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ytlz.baseproject.R;
import com.ytlz.baseproject.common.AppUserConstants;
import com.ytlz.baseproject.moudle.resopnse.SplashResponse;
import com.ytlz.baseproject.presenter.Splash.SplashPresenter;
import com.ytlz.baseproject.presenter.Splash.SplashView;
import com.ytlz.baseproject.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

public class SplashActivity extends BaseActivity implements BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String>, SplashView {

    @BindView(R.id.banner_guide_background)
    BGABanner bannerGuideBackground;
//    @BindView(R.id.tv_guide_skip)
//    TextView tvGuideSkip;
//    @BindView(R.id.btn_guide_enter)
//    Button btnGuideEnter;

    List<String> guideLists = new ArrayList<>();

    SplashPresenter splashPresenter;

    @Override
    public void initParms(Bundle parms) {
        steepStatusBar();

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(View view) {
        bannerGuideBackground.setDelegate(this);
        bannerGuideBackground.setAdapter(this);
        /**
         * 设置数据
         * 参数一为 图片的加载地址
         * 参数二为 每张图片的介绍内容，这里我们传空
         */
        splashPresenter = SplashPresenter.getInstance();
        splashPresenter.setSplashView(this);
        splashPresenter.loadImgs();
    }

    @Override
    public void setListener() {
        bannerGuideBackground.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                ActivityUtils.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

        ThreadUtils.executeBySingleWithDelay(new ThreadUtils.Task<String>() {

            @Override
            public String doInBackground() throws Throwable {
                String tokenStr = SPUtils.getInstance().getString(AppUserConstants.AppToken, "");
                if (AppUserConstants.ExitTag.equalsIgnoreCase(SPUtils.getInstance().getString(AppUserConstants.ExitApp))) {
                    ActivityUtils.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
                if (TextUtils.isEmpty(tokenStr)) {
                    ActivityUtils.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else {
                    ActivityUtils.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                //验证token是否过期，如果过期重新登录


                return "1";
            }

            @Override
            public void onSuccess(String result) {
                if ("1".equalsIgnoreCase(result)) {
                    Log.d(TAG, "onSuccess: --------" + result);
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFail(Throwable t) {

            }
        }, 10000, TimeUnit.SECONDS);


    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public void backPressed() {
        //拦截返回键，欢迎界面没有结束不让用户退出该界面
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        //设置点击事件，当用户点击广告时，跳转到相应链接
        showShortToast("点击了第" + position + "个");
        LogUtils.dTag("banner:model:" + model);

    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        Glide.with(this).load(model).apply(new RequestOptions().placeholder(R.drawable.image_eror).error(R.drawable.image_eror).dontAnimate().centerCrop()).into(itemView);
    }

    @Override
    public void verificationToken() {
        //验证token;那么请求图片的时候token不是已经验证了吗
    }

    @Override
    public void loadSplashImgs(SplashResponse splashResponse) {
        if (null == splashResponse || splashResponse.getData() == null) return;
        for (SplashResponse.DataBean dataBean : splashResponse.getData()) {
            guideLists.add(dataBean.getImgUrl());
        }
        bannerGuideBackground.setData(guideLists, null);
    }

    @Override
    public void loadSplashImgsError(String mes) {
        showShortToast(mes);
        //如果网络图片加载失败，是否要设置默认图片显示

    }
}
