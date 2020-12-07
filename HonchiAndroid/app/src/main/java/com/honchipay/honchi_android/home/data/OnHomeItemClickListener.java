package com.honchipay.honchi_android.home.data;

import android.view.View;

import com.honchipay.honchi_android.home.ui.homeAdapter;

public interface OnHomeItemClickListener {
    void onItemClick(homeAdapter.ViewHolder holder, View view, int position);
}