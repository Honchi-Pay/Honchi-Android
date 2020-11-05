package com.honchipay.honchi_android.profile.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.profile.data.ProfileRepository;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class EditProfileViewModel extends BaseViewModel {
    ProfileRepository repository = new ProfileRepository();
    public ObservableField<String> nickName = new ObservableField<>(SharedPreferencesManager.getInstance().getUserName());
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirm = new ObservableField<>();
    public MutableLiveData<Boolean> changeSuccess = new MutableLiveData<>();

    public void uploadUserNewInfo(File file) {
        if (!nickName.get().equals("")) {
            addDisposable(repository.updateUserProfile(nickName.get(), file)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                        @Override
                        public void onSuccess(@NonNull Response<Void> uploadResponse) {
                            changeSuccess.postValue(uploadResponse.isSuccessful() && uploadResponse.code() == 200);
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e("EditProfileViewModel", e.getMessage());
                            changeSuccess.postValue(false);
                        }
                    })
            );
        }
    }

    public void changeUserPassword() {
        if (password.get().equals(confirm.get())) {
            addDisposable(repository.changeUserPassword("")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                        @Override
                        public void onSuccess(@NonNull Response<Void> changeResponse) {
                            changeSuccess.postValue(changeResponse.isSuccessful() && changeResponse.code() == 200);
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e("EditProfileViewModel", e.getMessage());
                            changeSuccess.postValue(false);
                        }
                    })
            );
        }
    }
}
