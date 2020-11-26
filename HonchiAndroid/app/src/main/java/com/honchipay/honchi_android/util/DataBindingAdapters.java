package com.honchipay.honchi_android.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class DataBindingAdapters {
    @BindingAdapter("setImage")
    public static void setViewFromImageName(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).circleCrop().into(view);
    }
}
