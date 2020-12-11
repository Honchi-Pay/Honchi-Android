package com.honchipay.honchi_android.writing;

import android.util.Log;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.home.Data.newPost;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class writingViewModel extends BaseViewModel {
    private final writingRepository repository = new writingRepository();

    public void writing(String title, String content, File image, String category, String item, int lat, int lan){
        addDisposable(repository.writing(title,content,image,category,item,lat,lan).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>(){

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        if(voidResponse.code() == 200 && voidResponse.isSuccessful()){
                            Log.e("writingViewModel", String.valueOf(voidResponse.code()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("writingViewModel",e.getMessage());
                    }
                })
        );
    }

    public void modifyPost(){
        addDisposable(repository.modifyPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        if(voidResponse.code() == 200 && voidResponse.isSuccessful()){
                            Log.e("writingViewModel", String.valueOf(voidResponse.code()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("writingViewModel",e.getMessage());
                    }
                })
        );
    }

    public void deletePost(){
        addDisposable(repository.deletePost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {

                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        if(voidResponse.isSuccessful() && voidResponse.code() == 200) {
                            Log.e("writingViewModel", String.valueOf(voidResponse.code()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("writingViewModel",e.getMessage());
                    }
                })
        );
    }

}