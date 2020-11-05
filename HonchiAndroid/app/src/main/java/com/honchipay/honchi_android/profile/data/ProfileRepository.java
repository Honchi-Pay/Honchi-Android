package com.honchipay.honchi_android.profile.data;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import io.reactivex.Single;
import retrofit2.Response;

public class ProfileRepository {
    public Single<Response<UserProfileResponse>> getUserProfile(String name) {
        String token = SharedPreferencesManager.getInstance().getAccessToken();

        return HonchipayConnector.getInstance().getApi().getUserProfile(token, name);
    }
}
