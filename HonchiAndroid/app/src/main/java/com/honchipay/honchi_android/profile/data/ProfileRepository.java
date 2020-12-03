package com.honchipay.honchi_android.profile.data;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.io.File;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ProfileRepository extends BaseRepository {
    public Disposable getUserProfile(DisposableSingleObserver<Response<UserProfileResponse>> userProfileResponseObserver) {
        String name = SharedPreferencesManager.getInstance().getUserName();

        return wrappingSingle(HonchipayConnector.getInstance().getApi().getUserProfile(token, name), userProfileResponseObserver);
    }

    public Disposable updateUserProfile(String name, File file, DisposableSingleObserver<Response<Void>> uploadUserInfoObserver) {
        RequestBody nickName = RequestBody.create(name, MediaType.parse("multipart/form-data"));
        RequestBody rqFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part mpFile = MultipartBody.Part.createFormData("profileImage", file.getName(), rqFile);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().updateUserProfile(token, nickName, mpFile), uploadUserInfoObserver);
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
