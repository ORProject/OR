package com.project.or.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.or.R;
import com.project.or.dialog.CustomDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class WriteFragment extends Fragment {


    public WriteFragment() {
        // Required empty public constructor
    }


    public static WriteFragment newInstance() {
        WriteFragment fragment = new WriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_write, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btnDialog)
    public void showDialog() {
        final CustomDialog dialog = new CustomDialog();
        dialog.show(getFragmentManager(),dialog.getTag());
        dialog.setOnClickDialogButtonListener(new CustomDialog.OnClickDialogButtonListener() {
            @Override
            public void onClickDialogOk() {
                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onClickDialogCancel() {
                Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
