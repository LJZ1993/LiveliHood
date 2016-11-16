package com.example.administrator.livelihood.ui.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.base.BaseActivity;
import com.example.administrator.livelihood.bean.NoteBean;
import com.example.administrator.livelihood.util.DateUtil;
import com.example.administrator.livelihood.util.OnSaveListener;
import com.example.administrator.livelihood.util.RealmUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import io.realm.Realm;

/**
 * Created by Administrator on 2016/11/13.
 */
public class NoteContentActivity extends BaseActivity {
    private static final String TAG = "NoteContentActivityQQ";
    @Bind(R.id.m_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.m_nc_content)
    EditText mNcContent;

    DateUtil mDateUtil;
    String mCurrentTime;
    String mOnceTime;
    private boolean change = false;

    @Override
    protected void init() {
        mDateUtil = DateUtil.getInstance();
        mCurrentTime = mDateUtil.getCurrentTime();

        //itme的点击事件
        NoteBean bean = (NoteBean) getIntent().getSerializableExtra("bean");
        if (bean != null) {
            change = true;
            mOnceTime = bean.getTime();
            setToolbar(mOnceTime);
            String content = bean.getContent();
            mNcContent.setText(content);
            mNcContent.setSelection(content.length());
        } else {
            setToolbar(mCurrentTime);
        }
        //进入activity软键盘自动弹出
        showInput();


    }

    /**
     * 显示软键盘
     */
    private void showInput() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long id = Thread.currentThread().getId();
                Log.w(TAG, "id:" + id);
                InputMethodManager manager = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);
                manager.showSoftInput(mNcContent, 0);
            }
        }, 1000);
    }

    private void setToolbar(String time) {
        String currentDate = time.substring(5, 10);
        String currentHour = time.substring(10, 15);
        String weekDay = mDateUtil.getWeek();
        StringBuilder builder = new StringBuilder(weekDay + " ");
        builder.append(currentHour);
        setToolbar(mToolbar, currentDate, builder.toString(), true, new OnSaveListener() {
            @Override
            public void onSave() {
                //要保存数据
                save();
                finish();
            }
        });
    }

    /**
     * 保存数据
     */
    private void save() {
        String content = mNcContent.getText().toString();
        if (!change) {
            if (!TextUtils.isEmpty(content)) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                int id = sp.getInt("id", 0);

                final NoteBean bean = new NoteBean();
                bean.setId(id);
                bean.setContent(content);
                bean.setTime(mCurrentTime);
                RealmUtil.getDefaultInstance().save(bean);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("id", id + 1);
                editor.commit();
            }
        } else {
            //更新数据
            RealmUtil.getDefaultInstance().updataNote("time", mOnceTime, mCurrentTime, content);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_note;
    }

    public static void jumpToActivity(Context context) {
        context.startActivity(new Intent(context, NoteContentActivity.class));
    }

    public static void jumpToActivity(Context context, NoteBean bean) {
        Intent intent = new Intent(context, NoteContentActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
    }

}
