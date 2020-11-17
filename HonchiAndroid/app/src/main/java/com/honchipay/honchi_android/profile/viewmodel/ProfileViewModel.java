package com.honchipay.honchi_android.profile.viewmodel;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.chat.model.ChatListItem;
import com.honchipay.honchi_android.profile.data.ProfileRepository;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;

import org.jetbrains.annotations.NotNull;

import java.util.List;

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
    private final String TAG = ProfileViewModel.class.getSimpleName();

    public void getProfile(String name) {
        DisposableSingleObserver<Response<UserProfileResponse>> userProfileResponseObserver =
                new CustomDisposableSingleObserver<Response<UserProfileResponse>>(TAG) {
                    @Override
                    public void onSuccess(@NonNull Response<UserProfileResponse> profileResponseResponse) {
                        if (profileResponseResponse.isSuccessful() && profileResponseResponse.code() == 200) {
                            profileLiveData.postValue(profileResponseResponse.body());
                        }
                    }
                };

        addSingle(repository.getUserProfile(name), userProfileResponseObserver);
    }

    public void setUserRating(int userId, int rating) {
        DisposableSingleObserver<Response<Void>> userRatingObserver =
                new CustomDisposableSingleObserver<Response<Void>>(TAG) {
                    @Override
                    public void onSuccess(@NonNull Response<Void> ratingResponse) {
                        if (ratingResponse.isSuccessful() && ratingResponse.code() == 200) {
                            successStarLiveData.postValue(true);
                        }
                    }
                };

        addSingle(repository.sendUserEvaluation(userId, rating), userRatingObserver);
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

        addSingle(repository.withdrawFromService(), signOutServiceObserver);
    }

    public void signOutFromLogin() {
        signOutLiveData.setValue(true);
    }
}