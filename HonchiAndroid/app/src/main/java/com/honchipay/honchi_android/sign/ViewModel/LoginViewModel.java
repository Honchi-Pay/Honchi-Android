package com.honchipay.honchi_android.sign.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.Data.LoginRepository;
import com.honchipay.honchi_android.sign.Data.TokenResponseData;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    private final LoginRepository repository = new LoginRepository();
    public MutableLiveData<Boolean> tokenData = new MutableLiveData<>();

    public void login(String email, String password) {
        addDisposable(repository.doLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<TokenResponseData>>() {
                    @Override
                    public void onSuccess(@NonNull Response<TokenResponseData> tokenResponseDataResponse) {
                        if (tokenResponseDataResponse.isSuccessful() && tokenResponseDataResponse.code() == 200) {
                            TokenResponseData tokens = tokenResponseDataResponse.body();
                            SharedPreferencesManager sharedPreferences = SharedPreferencesManager.getInstance();

                            if (tokens != null) {
                                sharedPreferences.setAccessToken(tokens.getTokenType() + " " + tokens.getAccessToken());
                                sharedPreferences.setRefreshToken(tokens.getTokenType() + " " + tokens.getRefreshToken());
                            }

                            tokenData.postValue(true);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
                    }
                })
        );
    }
}