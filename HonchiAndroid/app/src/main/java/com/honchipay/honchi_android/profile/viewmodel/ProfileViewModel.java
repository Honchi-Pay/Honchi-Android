package com.honchipay.honchi_android.profile.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.profile.data.ProfileRepository;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class ProfileViewModel extends BaseViewModel {
    private final String TAG = ProfileViewModel.class.getSimpleName();
    private final ProfileRepository repository = new ProfileRepository();
    public final MutableLiveData<UserProfileResponse> profileLiveData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> signOutLiveData = new MutableLiveData<>();
    public final MutableLiveData<Boolean> successStarLiveData = new MutableLiveData<>();
    private int id = 0;

    public void getProfile() {
        addDisposable(repository.getUserProfile(new CustomDisposableSingleObserver<Response<UserProfileResponse>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<UserProfileResponse> profileResponseResponse) {
                if (profileResponseResponse.isSuccessful() && profileResponseResponse.code() == 200 && profileResponseResponse.body() != null) {
                    id = profileResponseResponse.body().getUserId();
                    profileLiveData.postValue(profileResponseResponse.body());
                }
            }
        }));
    }

    public void setUserRating(int rating) {
        addDisposable(repository.sendUserEvaluation(id, rating, new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> ratingResponse) {
                if (ratingResponse.isSuccessful() && ratingResponse.code() == 200) {
                    successStarLiveData.postValue(true);
                }
            }
        }));
    }

    public void signOutFromService() {
        addDisposable(repository.withdrawFromService(new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> deleteUserResponse) {
                signOutLiveData.postValue(deleteUserResponse.isSuccessful() && deleteUserResponse.code() == 200);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                Log.e(TAG, e.getMessage());
                signOutLiveData.postValue(false);
            }
        }));
    }

    public void signOutFromLogin() {
        signOutLiveData.setValue(true);
    }
}