package com.honchipay.honchi_android.util;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class CustomDisposableSingleObserver<T> extends DisposableSingleObserver<T> {
    private final String TAG;

    public CustomDisposableSingleObserver(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.e(TAG, e.getMessage());
    }
}
