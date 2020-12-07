package com.honchipay.honchi_android.writing;

import android.util.Log;

import com.honchipay.honchi_android.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class writingViewModel extends BaseViewModel {
    private final writingRepository repository = new writingRepository();

    public void writing(
            MultipartBody.Part titlePart,
            MultipartBody.Part contentPart,
            MultipartBody.Part imagesPart,
            MultipartBody.Part categoryPart,
            MultipartBody.Part itemPart,
            MultipartBody.Part latPart,
            MultipartBody.Part lonPart
    ){
        addDisposable(repository.writing(titlePart,contentPart,imagesPart,categoryPart,itemPart,latPart,lonPart).
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