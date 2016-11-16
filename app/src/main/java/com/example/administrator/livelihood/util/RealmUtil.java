package com.example.administrator.livelihood.util;

import com.example.administrator.livelihood.bean.NoteBean;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Administrator on 2016/11/14.f
 */
public class RealmUtil {
    public static final String NAME_REALM = "livehood.realm";

    private static Realm mRealm;

    public static RealmUtil getDefaultInstance() {
        mRealm = Realm.getDefaultInstance();
        RealmUtil realmUtil = new RealmUtil();
        return realmUtil;
    }

    /**
     * 保存数据
     *
     * @param t
     * @param <T>
     */
    public <T extends RealmObject> void save(final T t) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealm(t);
            }
        });
    }

    public <T extends RealmObject> List<? extends RealmObject> getData(T t) {
        RealmResults<? extends RealmObject> results = mRealm.where(t.getClass()).findAll();
        results.sort("time", Sort.DESCENDING);
        return mRealm.copyFromRealm(results);
    }

    public void updataNote(String fieldName, String onceTime, String currentTime, String content) {
        NoteBean bean = mRealm.where(NoteBean.class).equalTo("time", onceTime).findFirst();
        mRealm.beginTransaction();
        bean.setTime(currentTime);
        bean.setContent(content);
        mRealm.commitTransaction();
    }
}
