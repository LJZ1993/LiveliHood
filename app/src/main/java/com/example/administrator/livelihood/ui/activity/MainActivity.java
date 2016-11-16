package com.example.administrator.livelihood.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.base.BaseActivity;
import com.example.administrator.livelihood.ui.fragment.AlarmFragment;
import com.example.administrator.livelihood.ui.fragment.NoteFragment;
import com.example.administrator.livelihood.ui.fragment.WeatherFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.m_main_tablayout)
    TabLayout mMainTablayout;
    @Bind(R.id.m_main_viewpager)
    ViewPager mMainViewpager;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        final ArrayList<Fragment> mFragemntList = new ArrayList<>();
        mFragemntList.add(new AlarmFragment());
        mFragemntList.add(new WeatherFragment());
        mFragemntList.add(new NoteFragment());

        final String[] mTitleList = new String[]{"闹钟", "天气", "笔记"};

        mMainViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragemntList.get(position);
            }

            @Override
            public int getCount() {
                return mTitleList.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList[position];
            }
        });
        mMainTablayout.setupWithViewPager(mMainViewpager);
    }


}
