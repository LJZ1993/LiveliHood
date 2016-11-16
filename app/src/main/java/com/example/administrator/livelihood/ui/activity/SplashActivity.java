package com.example.administrator.livelihood.ui.activity;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/5.
 */
public class SplashActivity extends BaseActivity {


    @Bind(R.id.m_sp_imageview)
    ImageView mSpImageview;

    @Override
    protected void init() {
        mSpImageview.setAnimation(AnimationUtils.loadAnimation(this, R.anim.splashanim));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }
}
