package com.honchipay.honchi_android.profile.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.profile.data.ProfileRepository;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.io.File;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class EditProfileViewModel extends BaseViewModel {
    ProfileRepository repository = new ProfileRepository();
    public ObservableField<String> nickName = new ObservableField<>(SharedPreferencesManager.getInstance().getUserName());
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirm = new ObservableField<>();
    public MutableLiveData<Boolean> changeSuccess = new MutableLiveData<>();
    private final String TAG = EditProfileViewModel.class.getSimpleName();

    public void uploadUserNewInfo(File file) {
        if (!nickName.get().equals("")) {
            DisposableSingleObserver<Response<Void>> uploadUserInfoObserver =
                    new CustomDisposableSingleObserver<Response<Void>>(TAG) {
                        @Override
                        public void onSuccess(@NonNull Response<Void> uploadResponse) {
                            changeSuccess.postValue(uploadResponse.isSuccessful() && uploadResponse.code() == 200);
                        }
                    };

            addSingle(repository.updateUserProfile(nickName.get(), file), uploadUserInfoObserver);
        }
    }

    public void changeUserPassword() {
        if (password.get().equals(confirm.get())) {
            DisposableSingleObserver<Response<Void>> changePasswordObserver =
                    new CustomDisposableSingleObserver<Response<Void>>(TAG) {
                        @Override
                        public void onSuccess(@NonNull Response<Void> changeResponse) {
                            changeSuccess.postValue(changeResponse.isSuccessful() && changeResponse.code() == 200);
                        }
                    };

            addSingle(repository.changeUserPassword(""), changePasswordObserver);
        }
    }
}
