package com.honchipay.honchi_android.sign.ViewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.Data.SignRepository;
import com.honchipay.honchi_android.sign.Data.SignUpProcess;
import com.honchipay.honchi_android.sign.Data.SignUpRequest;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SignUpViewModel extends BaseViewModel {
    private final SignRepository repository = new SignRepository();
    public MutableLiveData<SignUpProcess> haveToNextPageLiveData = new MutableLiveData<>();

    public void checkFirstUser(String email) {
        addDisposable(repository.checkFirstTimeUser(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                            haveToNextPageLiveData.postValue(SignUpProcess.EMAIL);
                        } else {
                            haveToNextPageLiveData.postValue(SignUpProcess.FIRST);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
                        haveToNextPageLiveData.postValue(SignUpProcess.REJECT);
                    }
                })
        );
    }

    public void checkDuplicatedEmail(String email) {
        addDisposable(repository.checkDuplicatedEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                            haveToNextPageLiveData.postValue(SignUpProcess.EMAIL);
                        } else {
                            haveToNextPageLiveData.postValue(SignUpProcess.REJECT);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
                        haveToNextPageLiveData.postValue(SignUpProcess.REJECT);
                    }
                })
        );
    }

    public void checkAuthCode(String email, String code) {
        addDisposable(repository.checkAuthCode(email, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                            haveToNextPageLiveData.postValue(SignUpProcess.CODE);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
                    }
                })
        );
    }

    public void signUp(SignUpRequest signUpRequest) {
        addDisposable(repository.signUp(signUpRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@NonNull Response<Void> voidResponse) {
                        if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                            haveToNextPageLiveData.postValue(SignUpProcess.SIGN_UP);
                            SharedPreferencesManager.getInstance().setUserName(signUpRequest.getNickName());
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
