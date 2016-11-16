package com.example.administrator.livelihood.ui.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.adapter.AlarmAdapter;
import com.example.administrator.livelihood.base.BaseFragment;
import com.example.administrator.livelihood.bean.AlarmBean;
import com.example.administrator.livelihood.ui.activity.AlarmAddActivity;
import com.example.administrator.livelihood.ui.view.NoScrollListView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;

/**
 * 闹钟
 */
public class AlarmFragment extends BaseFragment implements View.OnTouchListener, View.OnClickListener {
    private static final String TAG = "AlarmFragmentQQ";

    @Bind(R.id.m_a_appbarlayout)
    AppBarLayout mAAppbarlayout;
    @Bind(R.id.m_a_listview)
    NoScrollListView mAListview;
    @Bind(R.id.m_a_setting)
    FloatingActionButton mASetting;
    @Bind(R.id.m_a_add)
    FloatingActionButton mAAdd;
    @Bind(R.id.m_a_coordinatorlayout)
    CoordinatorLayout mACoordinatorlayout;
    @Bind(R.id.m_a_fam)
    FloatingActionsMenu mAFam;
    @Bind(R.id.m_a_nestedscrollview)
    NestedScrollView mANestedscrollview;


    @Override
    protected void init() {
        initAppbarlayout();

        ArrayList<AlarmBean> mDataList = new ArrayList<>();
        AlarmAdapter adapter = new AlarmAdapter(mActivity, mDataList, R.layout.item_alarm);
        mAListview.setAdapter(adapter);
        //设置触摸事件 fbm的消失
        mACoordinatorlayout.setOnTouchListener(this);
        mANestedscrollview.setOnTouchListener(this);

        mASetting.setOnClickListener(this);
        mAAdd.setOnClickListener(this);

    }

    private void initAppbarlayout() {
        mAAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset == 0) {
//                    mATime.setVisibility(View.INVISIBLE);
//                } else if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
//                    //获取当前时间
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                    String currentTime = format.format(new Date());
//                    mATime.setVisibility(View.VISIBLE);
//                    mATime.setText(currentTime);
//                } else {
//                    mATime.setVisibility(View.INVISIBLE);
//                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_alarm;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mAFam.collapse();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //设置界面
            case R.id.m_a_setting:
                break;
            //闹钟添加界面
            case R.id.m_a_add:
                AlarmAddActivity.jumpActivity(mActivity);
                break;
        }
    }


}
