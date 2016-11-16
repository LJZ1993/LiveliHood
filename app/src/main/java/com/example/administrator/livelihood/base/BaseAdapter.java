package com.example.administrator.livelihood.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/13.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private List<T> mData;
    private int mLayoutId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mLongClickListener;

    public BaseAdapter(Context context, int layoutId) {
        this.mContext = context;
        mData = new ArrayList<>();
        this.mLayoutId = layoutId;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(mLayoutId, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        bindItem(mContext, holder, mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mLongClickListener.onItemLongClick(position);
                notifyDataSetChanged();
                return true;
            }
        });

    }

    protected abstract void bindItem(Context mContext, BaseViewHolder holder, T t);

    public void setData(List<T> data) {
        mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setOnItemClickListener(OnItemClickListener l) {
        this.mOnItemClickListener = l;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        this.mLongClickListener = longClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }
}
