package com.honchipay.honchi_android.home.Data;

import android.view.View;

import com.honchipay.honchi_android.home.Ui.homeAdapter;

public interface OnHomeItemClickListener {
    void onItemClick(homeAdapter.ViewHolder holder, View view, int position);
}