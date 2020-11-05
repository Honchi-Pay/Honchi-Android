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
    public MutableLiveData<Boolean> changeSuccess = new MutableLiveData<>();

    public void uploadUserNewInfo(File file) {
        if (!nickName.get().equals("")) {
            addDisposable(repository.updateUserProfile(nickName.get(), file)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                        @Override
                        public void onSuccess(@NonNull Response<Void> uploadResponse) {
                            if (uploadResponse.isSuccessful() && uploadResponse.code() == 200) {
                                changeSuccess.postValue(true);
                            }
                        }
                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e("EditProfileViewModel", e.getMessage());
                        }
                    })
            );
        }
    }
}
