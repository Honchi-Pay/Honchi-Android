package com.honchipay.honchi_android.base;

import com.honchipay.honchi_android.util.SharedPreferencesManager;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BaseRepository {
    public final String token = SharedPreferencesManager.getInstance().getAccessToken();

    public <T> Disposable wrappingSingle(Single<T> single, DisposableSingleObserver<T> observer) {
        return single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
}
