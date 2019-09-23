package com.ytlz.baseproject.presenter.Splash;

import com.ytlz.baseproject.moudle.resopnse.SplashResponse;
import com.ytlz.baseproject.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyb on 2019-09-23.
 */

public class SplashPresenter implements BasePresenter {

    private SplashView splashView;

    public void setSplashView(SplashView splashView) {
        this.splashView = splashView;
    }

    public static SplashPresenter getInstance() {
        return new SplashPresenter();
    }

    public void loadImgs(){
        SplashResponse response = new SplashResponse();
        List<SplashResponse.DataBean> dataBeanList = new ArrayList<>();
        SplashResponse.DataBean dataBean = new SplashResponse.DataBean();
        dataBean.setImgUrl("http://a.hiphotos.baidu.com/image/pic/item/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg");
        dataBean.setTips("图片1");
        dataBeanList.add(dataBean);
        SplashResponse.DataBean dataBean1 = new SplashResponse.DataBean();
        dataBean1.setImgUrl("http://b.hiphotos.baidu.com/image/pic/item/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg");
        dataBean1.setTips("图片2");
        dataBeanList.add(dataBean1);
        SplashResponse.DataBean dataBean2 = new SplashResponse.DataBean();
        dataBean2.setImgUrl("http://b.hiphotos.baidu.com/image/pic/item/0eb30f2442a7d9337119f7dba74bd11372f001e0.jpg");
        dataBean2.setTips("图片3");
        dataBeanList.add(dataBean2);
        response.setData(dataBeanList);
        splashView.loadSplashImgs(response);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }
}
