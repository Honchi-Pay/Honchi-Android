package com.honchipay.honchi_android.home.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.home.data.OnPostClickListener;
import com.honchipay.honchi_android.home.data.newPost;

import java.util.ArrayList;
import java.util.List;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder>{
    ArrayList<newPost> items = new ArrayList<newPost>();
    OnPostClickListener listener;
    homeActivity activity;

    public postAdapter(homeActivity activity){
        this.activity = activity;
    }

    public void notifyDataChanged(List<newPost> postList){
        items.addAll(postList);
        Log.e("homeFragment",items.toString());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_post,parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        newPost post = items.get(position);
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
        TextView category_textView;

        public ViewHolder(@NonNull View itemView, postAdapter postAdapter) {
            super(itemView);

            image_imageView = itemView.findViewById(R.id.post_image_imageView);
            title_textView = itemView.findViewById(R.id.post_title_textView);
            user_textView = itemView.findViewById(R.id.post_user_textView);
            location_textView = itemView.findViewById(R.id.post_location_textView);
            date_textView = itemView.findViewById(R.id.post_date_textView);
            category_textView = itemView.findViewById(R.id.post_category_button);

        }

        public void setItem(newPost item) {
            Glide.with(itemView.getContext()).load(item.getImage()).into(image_imageView);
            title_textView.setText(item.getTitle());
            user_textView.setText(item.getWriter());
            location_textView.setText(item.getAddress());
            date_textView.setText(item.getCreatedAt().toString());
            category_textView.setText(item.getItem());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        detailPostFragment fragment = new detailPostFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("position",item.getPostId());
                        fragment.setArguments(bundle);

                        activity.onFragmentChanged(fragment);
                    }
                }
            });

        }
    }

}
