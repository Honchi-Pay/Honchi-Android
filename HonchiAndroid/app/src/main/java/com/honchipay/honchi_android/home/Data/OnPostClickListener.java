package com.honchipay.honchi_android.home.data;

import android.view.View;

import com.honchipay.honchi_android.home.ui.postAdapter;


public interface OnPostClickListener {
    void onItemClick(postAdapter.ViewHolder holder, View view, int position);
}
