package com.example.administrator.livelihood.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.base.BaseActivity;
import com.example.administrator.livelihood.bean.AlarmBean;
import com.example.administrator.livelihood.ui.view.CustomePopWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Administrator on 2016/11/8.
 */
public class AlarmAddActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "AlarmSetActivityQQ";

    @Bind(R.id.m_aset_bell_tv)
    TextView mAsetBellTv;
    @Bind(R.id.m_aset_bell)
    LinearLayout mAsetBell;
    @Bind(R.id.m_aset_mark_tv)
    TextView mAsetMarkTv;
    @Bind(R.id.m_aset_mark)
    LinearLayout mAsetMark;
    @Bind(R.id.m_aset_shake_togglebutton)
    ToggleButton mAsetShakeTogglebutton;
    @Bind(R.id.m_aset_shake)
    LinearLayout mAsetShake;
    @Bind(R.id.m_aset_repeat_tv)
    TextView mAsetRepeatTv;
    @Bind(R.id.m_aset_repeat)
    LinearLayout mAsetRepeat;
    @Bind(R.id.timePicker)
    TimePicker timePicker;
    @Bind(R.id.m_aset_main)
    LinearLayout mAsetMain;

    private int mRepaet = 0;


    @Override
    protected void init() {
        mAsetRepeat.setOnClickListener(this);
        mAsetBell.setOnClickListener(this);
        mAsetMark.setOnClickListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_aset;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.m_aset_repeat:
                //底部弹出popwindow
                showPop(v);
                break;
            case R.id.m_aset_bell:
                //铃声
                AlarmBellActivity.jumpActivity(this);
                break;
            case R.id.m_aset_mark:
                break;
        }
    }

    private void showPop(View view) {
        final CustomePopWindow customePopWindow = new CustomePopWindow(this, view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //只想一次
                    case R.id.m_as_ll1:
                        mRepaet = 1;
                        break;
                    case R.id.m_as_ll2:
                        mRepaet = 2;
                        break;
                    case R.id.m_as_ll3:
                        mRepaet = 3;
                        break;
                    case R.id.m_as_ll4:
                        mRepaet = 4;
                        break;
                    case R.id.m_as_ll5:
                        mRepaet = 5;
                        break;
                }
            }
        });

    }

    public static void jumpActivity(Activity activity) {
        Intent intent = new Intent(activity, AlarmAddActivity.class);
        activity.startActivity(intent);
    }
}
