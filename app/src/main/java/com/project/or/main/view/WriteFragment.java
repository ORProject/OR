package com.project.or.main.view;

import android.app.Activity;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.or.R;
import com.project.or.common.MyApplication;
import com.project.or.dialog.CustomDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WriteFragment extends Fragment {

    @Bind(R.id.imgLeft)
    ImageView imgLeft;

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

    public static int REQUEST_GALLERY = 10000;

    @OnClick(R.id.btnGallery)
    public void selectPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {

            Glide.with(MyApplication.getContext())
                    .load(data.getData())
                    .centerCrop()
                    .into(imgLeft);

        }
    }

    @OnClick(R.id.btnWrite)
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

    @OnClick(R.id.btnCancel)
    public void cancelWrite() {
        ((MainActivity)getActivity()).mPager.setCurrentItem(0);
    }

}
