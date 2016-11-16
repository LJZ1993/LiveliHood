package com.example.administrator.livelihood.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.livelihood.R;
import com.example.administrator.livelihood.bean.BellBean;
import com.example.administrator.livelihood.ui.activity.AlarmBellActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 铃声选择界面
 */
public class AlarmBellAdapter extends RecyclerView.Adapter<AlarmBellAdapter.MyViewHolder> {


    private List<BellBean> mDataList;
    private AlarmBellActivity mActivity;

    public AlarmBellAdapter(AlarmBellActivity alarmBellActivity) {
        this.mActivity = alarmBellActivity;
        this.mDataList = new ArrayList<>();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bell, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindItem(position);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setData(List<BellBean> data) {
        this.mDataList = data;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.m_bell_name)
        TextView mBellName;
        @Bind(R.id.m_bell_time)
        TextView mBellTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(int position) {
            Log.w("bindItem",""+mDataList.get(position).getTitle());
            mBellName.setText(mDataList.get(position).getTitle());
            mBellTime.setText(mDataList.get(position).getTime() + "");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
