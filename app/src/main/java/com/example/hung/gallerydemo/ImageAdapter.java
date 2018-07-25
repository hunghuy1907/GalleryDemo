package com.example.hung.gallerydemo;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{
    private List<File> mPathResource;
    private LayoutInflater mInflater;
    private Context mContext;

    public ImageAdapter(List<File> mPathResource, Context mContext) {
        this.mPathResource = mPathResource;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mImgGallary.setImageURI(Uri.parse(mPathResource.get(position).toString()));
    }

    @Override
    public int getItemCount() {
        return mPathResource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgGallary;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgGallary = itemView.findViewById(R.id.img_gallary);
        }
    }
}
