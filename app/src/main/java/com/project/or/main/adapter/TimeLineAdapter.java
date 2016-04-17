package com.project.or.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.or.R;
import com.project.or.common.MyApplication;
import com.project.or.main.model.TimeLineItem;
import com.project.or.main.viewholder.TimeLineViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by INNO-09 on 2016-04-04.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TimeLineAdapterDataModel, TimeLineAdapterDataView {


    Context mContext;
    List<TimeLineItem> items = new ArrayList<TimeLineItem>();

    public TimeLineAdapter(Context context) {
        mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.view_item, parent, false);
        return new TimeLineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TimeLineViewHolder mHolder = (TimeLineViewHolder)holder;
        mHolder.setItems(items.get(position));
        mHolder.setTimeLineClickListener(new TimeLineViewHolder.TimeLineClickListener() {
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

    @Override
    public void add(TimeLineItem item) {
        items.add(item);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }
}
