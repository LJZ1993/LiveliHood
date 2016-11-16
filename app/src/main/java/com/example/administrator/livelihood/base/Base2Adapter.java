package com.example.administrator.livelihood.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */
public abstract class Base2Adapter<T> extends android.widget.BaseAdapter {
    private List<T> mDataList;
    private Context mContext;
    private int mLayoutId;

    public Base2Adapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mDataList = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(convertView.getContext()).inflate(mLayoutId, null);
        BaseViewHolder holder;
        if (convertView == null) {
            holder = new BaseViewHolder(convertView);
            bindItem(holder, mDataList.get(position));
            convertView.setTag(holder);
        } else {
            holder = (BaseViewHolder) convertView.getTag();
        }
        return view;
    }

    protected abstract void bindItem(BaseViewHolder holder, T t);


    public class BaseViewHolder {
        private View mConvertView;

        public BaseViewHolder(View convertView) {
            this.mConvertView = convertView;
        }

        private <T extends View> T getView(int viewId) {
            View view = mConvertView.findViewById(viewId);
            return (T) view;
        }

        public BaseViewHolder setText(int viewId, String text) {
            if (text != null) {
                TextView textView = getView(viewId);
                textView.setText(text);
            }
            return this;
        }

        public BaseViewHolder setToggle(int viewId, boolean checked) {
            ToggleButton tb = getView(viewId);
            tb.setChecked(checked);
            return this;
        }

    }

}
