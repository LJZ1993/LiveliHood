package com.example.administrator.livelihood;

import android.app.Application;

import com.example.administrator.livelihood.util.RealmUtil;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2016/11/9.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(RealmUtil.NAME_REALM)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
