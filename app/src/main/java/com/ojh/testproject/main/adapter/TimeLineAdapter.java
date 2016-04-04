package com.ojh.testproject.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ojh.testproject.R;
import com.ojh.testproject.common.MyApplication;
import com.ojh.testproject.main.data.TimeLineItem;
import com.ojh.testproject.main.view.TimeLineView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INNO-09 on 2016-04-04.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context mContext;
    List<TimeLineItem> items = new ArrayList<TimeLineItem>();

    public TimeLineAdapter(Context context) {
        mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.view_item, parent, false);
        return new TimeLineView(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TimeLineView mHolder = (TimeLineView)holder;
        mHolder.setItems(items.get(position));
        mHolder.setTimeLineClickListener(new TimeLineView.TimeLineClickListener() {
            @Override
            public void onIconClickListener(int postion) {
                Toast.makeText(MyApplication.getContext(), "icon, name:"+items.get(position).name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButtonClickListener(int postion) {
                Toast.makeText(MyApplication.getContext(), "btn, desc:"+items.get(position).desc, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(TimeLineItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

}
