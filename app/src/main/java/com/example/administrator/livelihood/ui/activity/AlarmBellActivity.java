package com.example.administrator.livelihood.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.adapter.AlarmBellAdapter;
import com.example.administrator.livelihood.base.BaseActivity;
import com.example.administrator.livelihood.bean.BellBean;
import com.example.administrator.livelihood.mvp.bell.BellContract;
import com.example.administrator.livelihood.mvp.bell.BellPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/10.
 */
public class AlarmBellActivity extends BaseActivity implements BellContract.View {
    private static final String TAG = "AlarmBellActivityQQ";
    private static final int CODE_FOR_WRITE_PERMISSION = 1;
    @Bind(R.id.m_bell_recyclerview)
    RecyclerView mBellRecyclerview;
    private AlarmBellAdapter mAdapter;

    @Override
    protected void init() {
        mBellRecyclerview.setHasFixedSize(true);
        mBellRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AlarmBellAdapter(this);
        mBellRecyclerview.setAdapter(mAdapter);

        Log.w(TAG,"onRequest33333333333$$$$$$$$$$$$$$$$$$$");


//        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        Log.w(TAG,"onRequest33333333333$$$$$$$$$$$$$$$$$$$"+hasWriteContactsPermission);
//        if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
//            Activity activty = this;
//            ActivityCompat.requestPermissions(activty, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    CODE_FOR_WRITE_PERMISSION);
//            Log.w(TAG,"onRequest33333333333");
//            return;
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.w(TAG,"onRequest");

        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意使用write

                BellPresenter presenter = new BellPresenter(this);
                presenter.dealData();
                Log.w(TAG,"onRequest11111");
            } else {
                //用户不同意，自行处理即可
                finish();
                Log.w(TAG,"onRequest2222222");
            }
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_bell;
    }

    public static void jumpActivity(Activity activity) {
        activity.startActivity(new Intent(activity, AlarmBellActivity.class));
    }

    /**
     * ui显示数据
     */

    @Override
    public void showData(List<BellBean> data) {
        mAdapter.setData(data);
        Log.w(TAG, data.get(0).getTitle());
    }

    @Override
    public void showError(String message) {
        Log.w(TAG, "message" + message);
    }


}
