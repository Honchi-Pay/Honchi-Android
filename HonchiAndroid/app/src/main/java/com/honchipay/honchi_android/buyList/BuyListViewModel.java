package com.honchipay.honchi_android.buyList;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class BuyListViewModel extends BaseViewModel {
    private final BuyListRepository repository = new BuyListRepository();
    public MutableLiveData<List<BuyListResponse>> buyListLiveData = new MutableLiveData<>();

    public void getBuyList(){
        addDisposable(repository.getBuyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<BuyListResponse>>>() {

                    @Override
                    public void onSuccess(@NonNull Response<List<BuyListResponse>> listResponse) {
                        if(listResponse.code() == 200 && listResponse.isSuccessful()){
                            buyListLiveData.postValue(listResponse.body());
                        }
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("BuyListViewModel", e.getMessage());
                    }
                }));
    }
}
