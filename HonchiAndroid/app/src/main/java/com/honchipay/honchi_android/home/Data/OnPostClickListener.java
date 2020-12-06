package com.honchipay.honchi_android.home.Data;

import android.view.View;

import com.honchipay.honchi_android.home.Ui.postAdapter;


public interface OnPostClickListener {
    void onItemClick(postAdapter.ViewHolder holder, View view, int position);
}
