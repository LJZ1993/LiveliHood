package com.example.administrator.livelihood.adapter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.base.BaseAdapter;
import com.example.administrator.livelihood.base.BaseViewHolder;
import com.example.administrator.livelihood.bean.NoteBean;

import java.util.List;

import io.realm.RealmConfiguration;

/**
 * Created by Administrator on 2016/11/13.
 */
public class NoteAdapter extends BaseAdapter<NoteBean> {
    public NoteAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    protected void bindItem(Context mContext, BaseViewHolder holder, NoteBean noteBean) {
        try {
            holder.setText(R.id.m_item_note_content, noteBean.getContent())
                    .setText(R.id.m_item_note_time, noteBean.getTime().substring(5, 10));
            Log.w("noteBean","noteBean "+noteBean.getId());
        } catch (NullPointerException e) {
            Log.w("bindItem","  bindItem的异常"+e);
        }
    }
}
