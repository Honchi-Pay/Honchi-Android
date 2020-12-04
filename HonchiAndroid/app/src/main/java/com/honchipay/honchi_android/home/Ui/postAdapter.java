package com.honchipay.honchi_android.home.Ui;

import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.home.Data.OnPostClickListener;
import com.honchipay.honchi_android.home.Data.newPost;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder> implements OnPostClickListener {
    ArrayList<newPost> items = new ArrayList<newPost>();
    OnPostClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.item_post,parent, false);

        return new ViewHolder(itemview, this);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(postAdapter.ViewHolder.this, v, position);
                    }
                }
            });

        }

        public void setItem(newPost item) {
            //image_imageView.setImageResource(item.getImage());
            title_textView.setText(item.getTitle());
            user_textView.setText(item.getWriter());
            //location_textView.setText(item.);
            date_textView.setText(item.getCreatedAt());
            category_textView.setText(item.getItem());
        }
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
}
