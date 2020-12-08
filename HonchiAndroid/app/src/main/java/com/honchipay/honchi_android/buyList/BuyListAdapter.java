package com.honchipay.honchi_android.buyList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.home.Data.newPost;

import java.util.ArrayList;
import java.util.List;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.ViewHolder> {
    private ArrayList<BuyListResponse> items = null;

    public void notifyDataChanged(List<BuyListResponse> postList){
        items.addAll(postList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BuyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buylist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyListAdapter.ViewHolder holder, int position) {
        BuyListResponse item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView goods_name;
        protected TextView price;
        protected TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.goods_name = itemView.findViewById(R.id.buyList_goods_textView);
            this.price = itemView.findViewById(R.id.buyList_price_textView);
            this.date = itemView.findViewById(R.id.buyList_date_textView);
        }

        public void setItem(BuyListResponse item){
            goods_name.setText(item.getTitle());
            price.setText(item.getPrice());
            date.setText(item.getCreated_at());
        }
    }
}