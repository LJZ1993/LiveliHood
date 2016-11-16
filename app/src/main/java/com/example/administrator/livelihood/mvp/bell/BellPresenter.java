package com.example.administrator.livelihood.mvp.bell;

import android.app.Activity;

import com.example.administrator.livelihood.ui.activity.AlarmBellActivity;
import com.example.administrator.livelihood.util.OnResultListener;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public class BellPresenter implements BellContract.Presenter {
    private AlarmBellActivity mActivity;
    private BellModel mModel;

    public BellPresenter(Activity activity) {
        this.mActivity = (AlarmBellActivity) activity;
        this.mModel = new BellModel();
    }

    @Override
    public void dealData() {
        mModel.obtainData(mActivity, new OnResultListener() {
            @Override
            public void onSuccess(List data) {
                mActivity.showData(data);

            }

            @Override
            public void onFailded(String message) {
                  mActivity.showError(message);
            }
        });

    }
}
