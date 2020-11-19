package com.honchipay.honchi_android.sign.viewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.data.SignRepository;
import com.honchipay.honchi_android.sign.data.SignUpProcess;
import com.honchipay.honchi_android.sign.data.SignUpRequest;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SignUpViewModel extends BaseViewModel {
    private final String TAG = SignUpViewModel.class.getSimpleName();
    private final SignRepository repository = new SignRepository();
    public MutableLiveData<SignUpProcess> haveToNextPageLiveData = new MutableLiveData<>();
    public final ObservableField<String> inputEmail = new ObservableField<>();
    public final ObservableField<String> inputAuthCode = new ObservableField<>();

    public void checkFirstUser() {
        DisposableSingleObserver<Response<Void>> checkFirstUserObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@io.reactivex.annotations.NonNull Response<Void> voidResponse) {
                if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                    haveToNextPageLiveData.postValue(SignUpProcess.EMAIL);
                } else {
                    haveToNextPageLiveData.postValue(SignUpProcess.FIRST);
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                super.onError(e);
                haveToNextPageLiveData.postValue(SignUpProcess.REJECT);
            }
        };

        addDisposable(repository.checkFirstTimeUser(inputEmail.get(), checkFirstUserObserver));
    }

    public void checkDuplicatedEmail() {
        DisposableSingleObserver<Response<Void>> checkDuplicatedEmailObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
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
        };

        addDisposable(repository.checkDuplicatedEmail(checkDuplicatedEmailObserver));
    }

    public void checkAuthCode() {
        DisposableSingleObserver<Response<Void>> checkAuthCodeObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> voidResponse) {
                if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                    haveToNextPageLiveData.postValue(SignUpProcess.CODE);
                }
            }
        };

        addDisposable(repository.checkAuthCode(inputAuthCode.get(), checkAuthCodeObserver));
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
