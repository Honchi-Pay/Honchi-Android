package com.honchipay.honchi_android.profile.data;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ProfileRepository extends BaseRepository {
    public Disposable getUserProfile(String name, DisposableSingleObserver<Response<UserProfileResponse>> userProfileResponseObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().getUserProfile(token, name), userProfileResponseObserver);
    }

    public Disposable updateUserProfile(String name, File file, DisposableSingleObserver<Response<Void>> uploadUserInfoObserver) {
        HashMap<String, RequestBody> requestHashMap = new HashMap<>();
        if (file != null) {
            requestHashMap.put("profile_image", RequestBody.create(file, MediaType.parse("multipart/form-data")));
        }
        requestHashMap.put("nick_name", RequestBody.create(name, MediaType.parse("multipart/form-data")));

        return wrappingSingle(HonchipayConnector.getInstance().getApi().updateUserProfile(token, requestHashMap), uploadUserInfoObserver);
    }

    public Disposable changeUserPassword(String password, DisposableSingleObserver<Response<Void>> changePasswordObserver) {
        HashMap<String, String> requestHashMap = new HashMap<>();
        requestHashMap.put("password", password);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().changePassword(token, requestHashMap), changePasswordObserver);
    }

    public Disposable sendUserEvaluation(int userId, int rating, DisposableSingleObserver<Response<Void>> userRatingObserver) {
        HashMap<String, Integer> requestHashMap = new HashMap<>();
        requestHashMap.put("targetId", userId);
        requestHashMap.put("star", rating);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().sendUserEvaluation(token, requestHashMap), userRatingObserver);
    }

    public Disposable withdrawFromService(DisposableSingleObserver<Response<Void>> signOutServiceObserver) {
        String name = SharedPreferencesManager.getInstance().getUserName();

        return wrappingSingle(HonchipayConnector.getInstance().getApi().withdrawFromService(token, name), signOutServiceObserver);
    }
}
