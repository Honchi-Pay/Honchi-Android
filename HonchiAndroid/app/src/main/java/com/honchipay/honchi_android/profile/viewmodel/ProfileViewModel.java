package com.honchipay.honchi_android.profile.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.profile.data.ProfileRepository;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;

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

    public void getProfile(String name) {
        DisposableSingleObserver<Response<UserProfileResponse>> userProfileResponseObserver =
                new CustomDisposableSingleObserver<Response<UserProfileResponse>>(TAG) {
                    @Override
                    public void onSuccess(@NonNull Response<UserProfileResponse> profileResponseResponse) {
                        if (profileResponseResponse.isSuccessful() && profileResponseResponse.code() == 200 && profileResponseResponse.body() != null) {
                            id = profileResponseResponse.body().getUserId();
                            profileLiveData.postValue(profileResponseResponse.body());
                        }
                    }
                };

        addDisposable(repository.getUserProfile(name, userProfileResponseObserver));
    }

    public void setUserRating(int rating) {
        DisposableSingleObserver<Response<Void>> userRatingObserver =
                new CustomDisposableSingleObserver<Response<Void>>(TAG) {
                    @Override
                    public void onSuccess(@NonNull Response<Void> ratingResponse) {
                        if (ratingResponse.isSuccessful() && ratingResponse.code() == 200) {
                            successStarLiveData.postValue(true);
                        }
                    }
                };

        addDisposable(repository.sendUserEvaluation(id, rating, userRatingObserver));
    }

    public void signOutFromService() {
        DisposableSingleObserver<Response<Void>> signOutServiceObserver =
                new CustomDisposableSingleObserver<Response<Void>>(TAG) {
                    @Override
                    public void onSuccess(@NonNull Response<Void> deleteUserResponse) {
                        signOutLiveData.postValue(deleteUserResponse.isSuccessful() && deleteUserResponse.code() == 200);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e(TAG, e.getMessage());
                        signOutLiveData.postValue(false);
                    }
                };

        addDisposable(repository.withdrawFromService(signOutServiceObserver));
    }

    public void signOutFromLogin() {
        signOutLiveData.setValue(true);
    }
}