package com.honchipay.honchi_android.sign.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.data.LoginRepository;
import com.honchipay.honchi_android.sign.data.TokenResponseData;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    private final LoginRepository repository = new LoginRepository();
    public MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

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

                            loginSuccess.postValue(true);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
                    }
                })
        );
    }

    public void forgotPassword(String email, String password) {
        addDisposable(repository.findUserPassword(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@NonNull Response<Void> findPasswordResponse) {
                        if (findPasswordResponse.isSuccessful() && findPasswordResponse.code() == 200) {
                            loginSuccess.postValue(true);
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