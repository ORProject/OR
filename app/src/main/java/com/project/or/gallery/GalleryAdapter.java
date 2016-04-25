package com.project.or.gallery;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.or.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

	private Context context;
	private LayoutInflater inflater;
	private Toolbar toolbar;
	private List<GalleryItem> files = new ArrayList<>(); //Collections.emptyList();
	private ArrayList<GalleryItem> galleryItems = new ArrayList<>();
	public int MAX = 2;

	public GalleryAdapter(Context context, Toolbar toolbar, ArrayList<GalleryItem> galleryItems) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.toolbar = toolbar;
		this.galleryItems = galleryItems;
	}


	private void matchingItems(){
		int position = 1;
		for (GalleryItem item : galleryItems) {
			for(GalleryItem file : files){
				if(file.sdcardPath.equals(item.sdcardPath)){
					file.isSeleted = item.isSeleted;
					file.position = position;
					position++;
					continue;
				}
			}
		}
		notifyDataSetChanged();
	}

	public void addAll(ArrayList<GalleryItem> files) {
		this.files = files;
		if(null != galleryItems) {
			matchingItems();
		}
		notifyDataSetChanged();
	}
	public ArrayList<GalleryItem> getGalleryItems(){
		return galleryItems;
	}

	private void add(GalleryItem item){
		if (MAX <= galleryItems.size()) {
			Toast.makeText(context, "사용갯수를 초과했습니다", Toast.LENGTH_SHORT).show();
			return;
		}
		item.isSeleted = true;
		galleryItems.add(item);
		matchingItems();
	}

	private void remove(GalleryItem item){
		item.isSeleted = false;
		galleryItems.remove(item.position - 1);
		matchingItems();
	}

	public void select(int position){
		GalleryItem item = files.get(position);
		boolean selected = item.isSeleted ? false : true;
		if(selected){
			add(item);
		}else{
			remove(item);
		}
		toolbar.setTitle(galleryItems.size() + "/" + MAX);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.view_gallery_item, parent, false);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		GalleryItem data = files.get(position);
		try {
			Glide.with(context).load(Uri.parse(data.sdcardPath)).centerCrop().into(holder.imageView);

				holder.check.setVisibility(data.isSeleted ? View.VISIBLE : View.GONE);
				holder.check.setSelected(data.isSeleted);
				holder.check.setText(""+data.position);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getItemCount() {
		return files.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		@Bind(R.id.custom_gallery_image) ImageView imageView;
		@Bind(R.id.custom_gallery_check) TextView check;

		public ViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

			DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
			int width = dm.widthPixels/3-10;

			itemView.setLayoutParams(new RelativeLayout.LayoutParams(width,width));

		}
	}
}
