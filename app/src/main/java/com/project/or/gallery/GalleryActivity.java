package com.project.or.gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.project.or.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.galleryRecyclerView)
    RecyclerView recyclerView;

    private Activity activity;
    private ArrayList<GalleryItem> galleryItems;
    private GalleryAdapter adapter;
    private Handler handler;

    public static String imageSelectResult = "imageSelectResult";
    public static String images = "images";
    public static final int REQ_PHOTO_SELECTED   = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        activity = this;
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        galleryItems = (ArrayList<GalleryItem>) getIntent().getExtras().getSerializable(GalleryActivity.images);

        initView();

        setTitle(galleryItems.size() + "/" + adapter.MAX);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.complete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_complete:
                galleryItems = adapter.getGalleryItems();
                Intent intent = new Intent();
                intent.putExtra(GalleryActivity.imageSelectResult, galleryItems);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        adapter = new GalleryAdapter(activity, toolbar, galleryItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(itemClickListener);

        attachToAdapter();
    }

    private ItemClickSupport.OnItemClickListener itemClickListener = new ItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            adapter.select(position);
        }
    };

    private void attachToAdapter() {
        handler = new Handler();
        new Thread() {

            @Override
            public void run() {
                Looper.prepare();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(getGalleryPhotos());
                    }
                });
                Looper.loop();
            }
        }.start();
    }

    private ArrayList<GalleryItem> getGalleryPhotos() {
        ArrayList<GalleryItem> galleryList = new ArrayList<GalleryItem>();

        try {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";

            @SuppressWarnings("deprecation")
            Cursor imagecursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
            if (imagecursor != null && imagecursor.getCount() > 0) {

                while (imagecursor.moveToNext()) {
                    GalleryItem item = new GalleryItem();
                    int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    item.sdcardPath = "file://" + imagecursor.getString(dataColumnIndex);
                    galleryList.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return galleryList;
    }
}
