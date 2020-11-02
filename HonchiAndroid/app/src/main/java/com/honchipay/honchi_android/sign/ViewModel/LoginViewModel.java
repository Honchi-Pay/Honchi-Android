package com.honchipay.honchi_android.sign.ViewModel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.Data.LoginRepository;
import com.honchipay.honchi_android.sign.Data.TokenResponseData;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    private final LoginRepository repository = new LoginRepository();
    public MutableLiveData<TokenResponseData> tokenData = new MutableLiveData<>();

    public void login(String email, String password) {
        addDisposable(repository.doLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<TokenResponseData>>() {
                    @Override
                    public void onSuccess(@NonNull Response<TokenResponseData> tokenResponseDataResponse) {
                        if (tokenResponseDataResponse.isSuccessful() && tokenResponseDataResponse.code() == 200) {
                            tokenData.postValue(tokenResponseDataResponse.body());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
                    }
                })
        );
    }
}