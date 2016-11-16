package com.example.administrator.livelihood.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.util.OnResultListener;
import com.example.administrator.livelihood.util.OnSaveListener;
import com.example.administrator.livelihood.util.SharedUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/5.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SOME_FEATURES_PERMISSIONS = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayout();

    protected void setToolbar(Toolbar toolbar, String title, String subTitle, boolean showMenu, final OnSaveListener listener) {
        setSupportActionBar(toolbar);
        //方法失效
        // toolbar.setTitle(title);
        //要设置成如下才成功
        getSupportActionBar().setTitle(title);
        toolbar.setSubtitle(subTitle);
        toolbar.setNavigationIcon(R.drawable.vector_drawable_ic_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (showMenu) {
            toolbar.inflateMenu(R.menu.toolbar_menu);
            //保存数据
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_save:
                            listener.onSave();
                            break;
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
