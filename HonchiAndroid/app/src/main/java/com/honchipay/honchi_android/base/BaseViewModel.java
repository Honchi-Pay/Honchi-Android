package com.honchipay.honchi_android.base;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseViewModel extends ViewModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

    @BindingAdapter("setImage")
    public static void setProfileImageUrl(ImageView view, String imageName) {
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        HonchipayConnector.getInstance().getApi().getImageByByte(token, imageName).enqueue(new Callback<ByteImage>() {
            @Override
            public void onResponse(@NotNull Call<ByteImage> call, @NotNull Response<ByteImage> response) {
                Glide.with(view.getContext()).load(response.body()).circleCrop().into(view);
            }

            @Override
            public void onFailure(@NotNull Call<ByteImage> call, @NotNull Throwable t) {
                Log.e(BaseViewModel.class.getSimpleName(), t.getMessage());
            }
        });
    }
}