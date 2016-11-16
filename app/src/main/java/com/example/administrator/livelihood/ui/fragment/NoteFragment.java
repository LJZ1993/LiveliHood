package com.example.administrator.livelihood.ui.fragment;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.adapter.NoteAdapter;
import com.example.administrator.livelihood.base.BaseAdapter;
import com.example.administrator.livelihood.base.BaseFragment;
import com.example.administrator.livelihood.bean.NoteBean;
import com.example.administrator.livelihood.ui.activity.NoteContentActivity;

import java.util.List;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Administrator on 2016/11/5.
 */
public class NoteFragment extends BaseFragment implements View.OnClickListener, BaseAdapter.OnItemClickListener, BaseAdapter.OnItemLongClickListener {
    @Bind(R.id.m_n_recyclerview)
    RecyclerView mNRecyclerview;
    @Bind(R.id.m_note_fab)
    FloatingActionButton mNoteFab;

    NoteAdapter mAdapter;
    List<NoteBean> mDataList;

    @Override
    protected void init() {
        mAdapter = new NoteAdapter(mActivity, R.layout.item_note);
        mNRecyclerview.setHasFixedSize(true);
        mNRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        mNRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mNoteFab.setOnClickListener(this);

        initData();

    }

    /**
     * 从数据库中获取数据
     */
    private void initData() {
        //mDataList = (List<NoteBean>) RealmUtil.getDefaultInstance().getData(new NoteBean());
        Realm realm = Realm.getDefaultInstance();
        RealmResults<NoteBean> results = realm.where(NoteBean.class).findAllSorted("id", Sort.DESCENDING);
        mDataList = realm.copyFromRealm(results);
        mAdapter.setData(mDataList);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_note;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.m_note_fab:
                NoteContentActivity.jumpToActivity(mActivity);
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        NoteBean bean = mDataList.get(position);
        NoteContentActivity.jumpToActivity(mActivity, bean);
    }

    /**
     * 长按删除
     *
     * @param position
     */
    @Override
    public void onItemLongClick(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage("是否删除这条笔记")
                .setTitle("提示")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = mDataList.get(position).getId();
                        Realm realm = Realm.getDefaultInstance();
                        NoteBean bean = realm.where(NoteBean.class).equalTo("id", id).findFirst();
                        realm.beginTransaction();
                        bean.deleteFromRealm();
                        realm.commitTransaction();
                        dialog.dismiss();
                        mDataList.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                });
        builder.create().show();
    }
}
