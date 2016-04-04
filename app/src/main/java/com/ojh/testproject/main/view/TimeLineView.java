package com.ojh.testproject.main.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ojh.testproject.R;
import com.ojh.testproject.common.MyApplication;
import com.ojh.testproject.main.data.TimeLineItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by INNO-09 on 2016-04-04.
 */
public class TimeLineView extends RecyclerView.ViewHolder {

    @Bind(R.id.img) ImageView imgIcon;
    @Bind(R.id.txt) public TextView txtName;
    @Bind(R.id.txt2) TextView txtDesc;
    @Bind(R.id.radio1) RadioButton radioOne;
    @Bind(R.id.radio2) RadioButton radioTwo;
    @Bind(R.id.btn) Button btn;

    public TimeLineView(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItems(TimeLineItem mItem) {
        txtName.setText(mItem.name);
        txtDesc.setText(mItem.desc);

        Glide.with(MyApplication.getContext())
                .load(mItem.iconUrl)
                .crossFade()
                .into(imgIcon);
    }

    @OnClick(R.id.img)
    public void iconClick() {
        if(mListener!=null) {
            mListener.onIconClickListener(getAdapterPosition());
        }
    }

    @OnClick(R.id.btn)
    public void btnClick() {
        if(mListener!=null) {
            mListener.onButtonClickListener(getAdapterPosition());
        }
    }

    public interface TimeLineClickListener {
        public void onIconClickListener(int postion);
        public void onButtonClickListener(int postion);
    }

    TimeLineClickListener mListener;

    public void setTimeLineClickListener(TimeLineClickListener listener) {
        mListener=listener;
    }

}
