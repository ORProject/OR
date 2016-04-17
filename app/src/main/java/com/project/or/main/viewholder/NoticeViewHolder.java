package com.project.or.main.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.or.R;
import com.project.or.common.MyApplication;
import com.project.or.main.model.NoticeItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 01071724654 on 2016-04-17.
 */
public class NoticeViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.img_notice)
    ImageView imgNotice;
    @Bind(R.id.txt_notice)
    TextView txtNotice;

    NoticeItem mItem;

    public NoticeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setNoticeItem(NoticeItem item) {
        mItem=item;

        mItem.leftItem="치킨";
        mItem.rightItem="피자";

        String strNotice = item.leftItem+" VS "+item.rightItem;
        txtNotice.setText(strNotice);

        Glide.with(MyApplication.getContext())
                .load(mItem.imgUrl)
                .crossFade()
                .into(imgNotice);
    }


}
