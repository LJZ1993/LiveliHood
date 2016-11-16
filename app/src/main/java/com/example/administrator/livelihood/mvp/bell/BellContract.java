package com.example.administrator.livelihood.mvp.bell;

import android.content.Context;

import com.example.administrator.livelihood.bean.BellBean;
import com.example.administrator.livelihood.util.OnResultListener;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */
public interface BellContract {

    interface View {
        void showData(List<BellBean> data);
        void showError(String message);
    }

    interface Model {
        void obtainData(Context context, OnResultListener listener);

    }

    interface Presenter {
        void dealData();
    }
}
