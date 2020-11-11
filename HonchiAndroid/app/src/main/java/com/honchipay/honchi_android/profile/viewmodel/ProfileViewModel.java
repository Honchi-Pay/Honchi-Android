package com.honchipay.honchi_android.profile.viewmodel;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.profile.data.ProfileRepository;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ProfileViewModel extends BaseViewModel {
    private final ProfileRepository repository = new ProfileRepository();
    public MutableLiveData<UserProfileResponse> profileLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> signOutLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> successStarLiveData = new MutableLiveData<>();

    public void getProfile(String name) {
        addDisposable(repository.getUserProfile(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<UserProfileResponse>>() {
                    @Override
                    public void onSuccess(@NonNull Response<UserProfileResponse> profileResponseResponse) {
                        if (profileResponseResponse.isSuccessful() && profileResponseResponse.code() == 200) {
                            profileLiveData.postValue(profileResponseResponse.body());
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("ProfileViewModel", e.getMessage());
                    }
                })
        );
    }

    public void setUserRating(int userId, int rating) {
        addDisposable(repository.sendUserEvaluation(userId, rating)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@NonNull Response<Void> ratingResponse) {
                        if (ratingResponse.isSuccessful() && ratingResponse.code() == 200) {
                            successStarLiveData.postValue(true);
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("ProfileViewModel", e.getMessage());
                    }
                })
        );
    }

    public void signOutFromLogin() {
        signOutLiveData.setValue(true);
    }

    public void signOutFromService() {
        addDisposable(repository.withdrawFromService()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Void>>() {
                    @Override
                    public void onSuccess(@NonNull Response<Void> deleteUserResponse) {
                        signOutLiveData.postValue(deleteUserResponse.isSuccessful() && deleteUserResponse.code() == 200);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("ProfileViewModel", e.getMessage());
                        signOutLiveData.postValue(false);
                    }
                })
        );
    }
}