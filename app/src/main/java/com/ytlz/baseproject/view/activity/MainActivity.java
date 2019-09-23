package com.ytlz.baseproject.view.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ytlz.baseproject.R;
import com.ytlz.baseproject.view.BaseActivity;

public class MainActivity extends BaseActivity {
    @Override
    public void initParms(Bundle parms) {
        hideActionBar();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

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
        doubleClickExitApp();
    }


}
