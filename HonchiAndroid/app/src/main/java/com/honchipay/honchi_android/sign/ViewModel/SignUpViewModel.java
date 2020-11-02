package com.honchipay.honchi_android.sign.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.Data.SignRepository;
import com.honchipay.honchi_android.sign.Data.SignUpProcess;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
                        haveToNextPageLiveData.postValue(SignUpProcess.FIRST);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
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
                        haveToNextPageLiveData.postValue(SignUpProcess.EMAIL);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("LoginViewModel", e.getMessage());
                    }
                })
        );
    }
}
