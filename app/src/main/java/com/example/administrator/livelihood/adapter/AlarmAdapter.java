package com.example.administrator.livelihood.adapter;


import android.content.Context;
import android.widget.Adapter;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.base.Base2Adapter;
import com.example.administrator.livelihood.base.BaseAdapter;
import com.example.administrator.livelihood.base.BaseViewHolder;
import com.example.administrator.livelihood.bean.AlarmBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class AlarmAdapter extends Base2Adapter<AlarmBean> {


    public AlarmAdapter(Context context, List<AlarmBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    protected void bindItem(BaseViewHolder holder, AlarmBean alarmBean) {
        holder.setText(R.id.m_item_a_time, alarmBean.getBell()).setToggle(R.id.m_item_a_switch, false);
    }
}
