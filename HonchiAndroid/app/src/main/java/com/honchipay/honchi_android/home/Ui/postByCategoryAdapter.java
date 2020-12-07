package com.honchipay.honchi_android.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.home.data.getPost;
import com.honchipay.honchi_android.home.ui.detailPostFragment;
import com.honchipay.honchi_android.home.ui.homeActivity;

import java.util.ArrayList;

public class postByCategoryAdapter extends RecyclerView.Adapter<postByCategoryAdapter.ViewHolder> {
    public ArrayList<getPost> items = new ArrayList<getPost>();
    homeActivity activity;
    public postByCategoryAdapter(homeActivity activity){
        this.activity= activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_post, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull postByCategoryAdapter.ViewHolder holder, int position) {
        getPost post = items.get(position);
        holder.setItem(post);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_imageView;
        TextView title_textView;
        TextView user_textView;
        TextView location_textView;
        TextView date_textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_imageView = itemView.findViewById(R.id.post_image_imageView);
            title_textView = itemView.findViewById(R.id.post_title_textView);
            user_textView = itemView.findViewById(R.id.post_user_textView);
            location_textView = itemView.findViewById(R.id.post_location_textView);
            date_textView = itemView.findViewById(R.id.post_date_textView);
        }

        public void setItem(getPost item) {
            Glide.with(itemView.getContext()).load(item.getImage()).into(image_imageView);
            title_textView.setText(item.getTitle());
            user_textView.setText(item.getWriter());
            location_textView.setText(item.getAddress());
            date_textView.setText(item.getCreateAt());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailPostFragment fragment = new detailPostFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("position",item.getPostId());
                    fragment.setArguments(bundle);

                    activity.onFragmentChanged(fragment);
                }
            });

        }
    }
}
