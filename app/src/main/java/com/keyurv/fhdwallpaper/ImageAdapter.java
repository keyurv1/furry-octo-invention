package com.keyurv.fhdwallpaper;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.PostHolder> {

    Context context;
    List<Item> imageList;

    public ImageAdapter(Context context , List<Item> imageList){
        this.context = context;
        this.imageList = imageList;
    }


    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new PostHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Item item = imageList.get(position);
        holder.setImageView(item.getImageUrl());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FullscreenActivity.class)

                        .putExtra("largeImageURL",imageList.get(position).getImageUrl()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class PostHolder extends  RecyclerView.ViewHolder{

        ImageView imageView;

        View view;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setImageView(String url){
            imageView = view.findViewById(R.id.imageview);
            Glide.with(context).load(url).placeholder(R.drawable.loading).into(imageView);

        }





    }

}
