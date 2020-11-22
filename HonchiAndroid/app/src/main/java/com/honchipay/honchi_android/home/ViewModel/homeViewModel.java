package com.honchipay.honchi_android.home.ViewModel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.home.Data.getPost;
import com.honchipay.honchi_android.home.Data.homeRepository;
import com.honchipay.honchi_android.home.Data.newPost;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class homeViewModel extends BaseViewModel {
    private final homeRepository repository = new homeRepository();
    public MutableLiveData<newPost> newPostLiveData = new MutableLiveData<>();
    public MutableLiveData<getPost> getPostLiveData = new MutableLiveData<>();

    public void getNewPost(String category) {
        addDisposable(repository.getNewPost(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<newPost>>() {

                    @Override
                    public void onSuccess(@NonNull Response<newPost> newPostResponse) {
                        if (newPostResponse.isSuccessful() && newPostResponse.code() == 200) {
                            newPostLiveData.postValue(newPostResponse.body());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("homeViewModel", e.getMessage());
                    }
                })
        );
    }

    public void getPostList(String category, String item, int dist) {
        addDisposable((Disposable) repository.getPostList(category, item, dist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<getPost>>() {
                    @Override
                    public void onSuccess(@NonNull Response<getPost> getPostResponse) {
                        if (getPostResponse.isSuccessful() && getPostResponse.code() == 200) {
                            getPostLiveData.postValue(getPostResponse.body());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("homeViewModel", e.getMessage());
                    }
                })
        );
    }
}