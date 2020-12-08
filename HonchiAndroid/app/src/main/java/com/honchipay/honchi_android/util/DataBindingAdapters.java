package com.honchipay.honchi_android.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.network.HonchipayConnector;

public class DataBindingAdapters {
    @BindingAdapter("setImage")
    public static void setViewFromImageName(ImageView view, String imageName) {
        if (imageName == null) {
            Glide.with(view.getContext()).load(R.drawable.default_profile).circleCrop().into(view);
        } else {
            GlideUrl glideUrl = new GlideUrl(HonchipayConnector.baseURL + "/image/" + imageName,
                    new LazyHeaders.Builder().addHeader("Authorization", SharedPreferencesManager.getInstance().getAccessToken()).build());
            Glide.with(view.getContext()).load(glideUrl).error(R.drawable.default_profile).circleCrop().into(view);
        }
    }
}
