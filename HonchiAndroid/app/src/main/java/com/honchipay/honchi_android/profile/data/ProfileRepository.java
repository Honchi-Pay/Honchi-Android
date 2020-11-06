package com.honchipay.honchi_android.profile.data;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ProfileRepository {
    String token = SharedPreferencesManager.getInstance().getAccessToken();

    public Single<Response<UserProfileResponse>> getUserProfile(String name) {
        return HonchipayConnector.getInstance().getApi().getUserProfile(token, name);
    }

    public Single<Response<Void>> updateUserProfile(String name, File file) {
        HashMap<String, RequestBody> requestHashMap = new HashMap<>();
        if (file != null) {
            requestHashMap.put("profile_image", RequestBody.create(file, MediaType.parse("multipart/form-data")));
        }
        requestHashMap.put("nick_name", RequestBody.create(name, MediaType.parse("multipart/form-data")));
        return HonchipayConnector.getInstance().getApi().updateUserProfile(token, requestHashMap);
    }

    public Single<Response<Void>> changeUserPassword(String password) {
        HashMap<String, String> requestHashMap = new HashMap<>();
        requestHashMap.put("password", password);
        return HonchipayConnector.getInstance().getApi().changePassword(token, requestHashMap);
    }

    public Single<Response<Void>> sendUserEvaluation(int rating) {
        HashMap<String, Integer> requestHashMap = new HashMap<>();
        requestHashMap.put("star", rating);
        return HonchipayConnector.getInstance().getApi().sendUserEvaluation(token, requestHashMap);
    }

    public Single<Response<Void>> withdrawFromService() {
        String name = SharedPreferencesManager.getInstance().getUserName();
        return HonchipayConnector.getInstance().getApi().withdrawFromService(token, name);
    }
}
