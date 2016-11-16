package com.example.administrator.livelihood.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/14.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View mItemView;
    private SparseArray<View> mViewList;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mItemView = itemView;
        mViewList = new SparseArray<>();
    }

    private <T extends View> T getView(int viewId) {
        View view=mViewList.get(viewId);
        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViewList.put(viewId,view);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
}
