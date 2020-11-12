package com.honchipay.honchi_android.buyList.ViewModel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.buyList.Model.BuyListRepository;
import com.honchipay.honchi_android.buyList.Model.DetailBuyList;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class BuyListViewModel extends BaseViewModel {
    public MutableLiveData<DetailBuyList> buyListLiveData = new MutableLiveData<>();
    private final BuyListRepository repository = new BuyListRepository();

    public void getBuyList() {
        addDisposable(repository.getBuyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<DetailBuyList>>() {
                    @Override
                    public void onSuccess(Response<DetailBuyList> buyListResponse) {
                        if (buyListResponse.isSuccessful() && buyListResponse.code() == 200) {
                            buyListLiveData.postValue(buyListResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("BuyListViewModel", e.getMessage());
                    }
                })
        );
    }

}
