package com.example.administrator.livelihood.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.administrator.livelihood.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/8.
 */
public class CustomePopWindow {
    @Bind(R.id.m_as_ll1)
    LinearLayout mAsLl1;
    @Bind(R.id.m_as_ll2)
    LinearLayout mAsLl2;
    @Bind(R.id.m_as_ll3)
    LinearLayout mAsLl3;
    @Bind(R.id.m_as_ll4)
    LinearLayout mAsLl4;
    @Bind(R.id.m_as_ll5)
    LinearLayout mAsLl5;
    @Bind(R.id.m_as_ll)
    LinearLayout mAsLl;
    PopupWindow popupWindow;

    public CustomePopWindow(Context context, View view, View.OnClickListener itemClicklistener) {
        View mView = LayoutInflater.from(context).inflate(R.layout.menu_alarmset, null);
        ButterKnife.bind(this, mView);
        popupWindow = new PopupWindow(mView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.GREEN));
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        mAsLl1.setOnClickListener(itemClicklistener);
        mAsLl2.setOnClickListener(itemClicklistener);
        mAsLl3.setOnClickListener(itemClicklistener);
        mAsLl4.setOnClickListener(itemClicklistener);
        mAsLl5.setOnClickListener(itemClicklistener);
        popupWindow.dismiss();

    }
}
