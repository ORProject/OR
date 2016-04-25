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
import com.project.or.gallery.GalleryActivity;
import com.project.or.gallery.GalleryItem;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WriteFragment extends Fragment {

    @Bind(R.id.imgLeft)
    ImageView imgLeft;
    @Bind(R.id.imgRight)
    ImageView imgRight;

    private ArrayList<GalleryItem> galleryItems = new ArrayList<GalleryItem>();

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

    @OnClick(R.id.btnGallery)
    public void moveToGallery() {
        Intent intent = new Intent(getContext(), GalleryActivity.class);
        intent.putExtra(GalleryActivity.images, galleryItems);
        startActivityForResult(intent, GalleryActivity.REQ_PHOTO_SELECTED);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GalleryActivity.REQ_PHOTO_SELECTED && resultCode == Activity.RESULT_OK) {

            imgLeft.setImageResource(R.drawable.gallery_empty);
            imgRight.setImageResource(R.drawable.gallery_empty);

            if(null != data) {
                ArrayList<GalleryItem> item = (ArrayList<GalleryItem>) data.getExtras().get(GalleryActivity.imageSelectResult);
                galleryItems = item;
                if(item.size() >= 1) {
                    Glide.with(MyApplication.getContext())
                            .load(item.get(0).sdcardPath)
                            .centerCrop()
                            .into(imgLeft);
                }

                if(item.size() >= 2 ) {
                    Glide.with(MyApplication.getContext())
                            .load(item.get(1).sdcardPath)
                            .centerCrop()
                            .into(imgRight);
                }
            }
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
