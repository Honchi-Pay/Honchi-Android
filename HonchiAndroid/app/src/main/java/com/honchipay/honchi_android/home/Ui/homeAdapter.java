package com.honchipay.honchi_android.home.Ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.home.Data.homeItem;
import com.honchipay.honchi_android.home.Data.OnHomeItemClickListener;

import java.util.ArrayList;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.ViewHolder> implements OnHomeItemClickListener {
    ArrayList<homeItem> items = new ArrayList<homeItem>();
    OnHomeItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_home, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        homeItem item = items.get(position);
        holder.setItem(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_textView;
        ImageView image_imageView;

        public ViewHolder(@NonNull View itemView, final OnHomeItemClickListener listener) {
            super(itemView);

            title_textView = itemView.findViewById(R.id.home_item_textView);
            image_imageView = itemView.findViewById(R.id.home_item_imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(homeItem item) {
            title_textView.setText(item.getTitle());
            image_imageView.setImageResource(item.getImage());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(homeItem item) {
        items.add(item);
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

}