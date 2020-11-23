package com.honchipay.honchi_android.util;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.base.ByteImage;
import com.honchipay.honchi_android.network.HonchipayConnector;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataBindingAdapters {
    @BindingAdapter("setImage")
    public static void setViewFromImageName(ImageView view, String imageName) {
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        HonchipayConnector.getInstance().getApi().getImageByByte(token, imageName).enqueue(new Callback<ByteImage>() {
            @Override
            public void onResponse(@NotNull Call<ByteImage> call, @NotNull Response<ByteImage> response) {
                Glide.with(view.getContext()).load(response.body().getImages()).circleCrop().into(view);
            }

            @Override
            public void onFailure(@NotNull Call<ByteImage> call, @NotNull Throwable t) {
                Log.e(BaseViewModel.class.getSimpleName(), t.getMessage());
            }
        });
    }
}
