package com.honchipay.honchi_android.home.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.home.Data.detailPost;
import com.honchipay.honchi_android.home.Data.getPost;
import com.honchipay.honchi_android.home.Data.homeRepository;
import com.honchipay.honchi_android.home.Data.newPost;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class homeViewModel extends BaseViewModel {
    private final homeRepository repository = new homeRepository();
    public MutableLiveData<List<newPost>> newPostLiveData = new MutableLiveData<>();
    public MutableLiveData<List<getPost>> getPostLiveData = new MutableLiveData<>();
    public MutableLiveData<detailPost> getDetailLiveData = new MutableLiveData<>();

    public void getNewPost(String category) {
        addDisposable(repository.getNewPost(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<newPost>>>() {
                    @Override
                    public void onSuccess(@NonNull Response<List<newPost>> newPostResponse) {
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
        addDisposable(repository.getPostList(category, item, dist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<getPost>>>() {
                    @Override
                    public void onSuccess(@NonNull Response<List<getPost>> getPostResponse) {
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

    public void searchPost(String title, int dist) {
        addDisposable(repository.searchPost(title, dist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<newPost>>>(
                ) {
                    @Override
                    public void onSuccess(@NonNull Response<List<newPost>> newPostResponse) {
                        if (newPostResponse.isSuccessful() && newPostResponse.code() == 200) {
                            newPostLiveData.postValue(newPostResponse.body());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("homeViewModel", e.getMessage());
                    }
                }));
    }

    public void detailPost(int postID) {
        addDisposable(repository.detailPost(postID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<detailPost>>() {

                    @Override
                    public void onSuccess(@NonNull Response<detailPost> detailPostResponse) {
                        if (detailPostResponse.isSuccessful()) {
                            getDetailLiveData.postValue(detailPostResponse.body());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("homeViewModel", e.getMessage());
                    }
                }));

    }


}
