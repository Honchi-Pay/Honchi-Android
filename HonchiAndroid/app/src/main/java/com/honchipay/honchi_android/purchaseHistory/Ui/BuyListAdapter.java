package com.honchipay.honchi_android.purchaseHistory.Ui;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.purchaseHistory.Model.BuyListData;
import com.honchipay.honchi_android.purchaseHistory.Model.BuyListDetailItem;

import java.util.ArrayList;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.ViewHolder> {
    private ArrayList<BuyListData> purchaseList = null;

    @NonNull
    @Override
    public BuyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buylist_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyListAdapter.ViewHolder holder, int position) {
        holder.goods_name.setText(purchaseList.get(position).getGoods_name());
        holder.price.setText(purchaseList.get(position).getPrice());
        holder.date.setText(purchaseList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView goods_name;
        protected TextView price;
        protected TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.goods_name = itemView.findViewById(R.id.purchaseItem_goods_textView);
            this.price = itemView.findViewById(R.id.purchaseItem_price_textView);
            this.date = itemView.findViewById(R.id.purchaseItem_date_textView);
        }
    }
}
