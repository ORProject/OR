package com.project.or.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.or.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by INNO-09 on 2016-04-06.
 */
public class CustomDialog extends DialogFragment {

    public CustomDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.CustomDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_custom, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.btnCancel)
    public void OnClickCancel() {
        if(mListener != null) {
            mListener.onClickDialogCancel();
        }
    }

    @OnClick(R.id.btnOk)
    public void OnClickOk() {
        if(mListener != null) {
            mListener.onClickDialogOk();
        }
    }

    public interface OnClickDialogButtonListener {
        public void onClickDialogOk();
        public void onClickDialogCancel();
    }

    OnClickDialogButtonListener mListener;

    public void setOnClickDialogButtonListener(OnClickDialogButtonListener listener) {
        mListener=listener;
    }


}
