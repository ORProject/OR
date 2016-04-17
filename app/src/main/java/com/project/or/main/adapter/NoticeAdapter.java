package com.project.or.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.or.R;
import com.project.or.main.model.NoticeItem;
import com.project.or.main.viewholder.NoticeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 01071724654 on 2016-04-17.
 */
public class NoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NoticeAdapterDataModel, NoticeAdapterDataView{

    Context mContext;
    List<NoticeItem> items = new ArrayList<NoticeItem>();

    public NoticeAdapter(Context context) {
        mContext=context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.view_notice, parent, false);
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NoticeViewHolder)holder).setNoticeItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void add(NoticeItem item) {
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
